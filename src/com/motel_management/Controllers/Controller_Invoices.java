package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.*;
import com.motel_management.Views.Configs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_Invoices {
    public Controller_Invoices() {
        super();
    }

    public static HashMap<String, String> getLastInvoice(String roomIdValue) {
        return InvoiceDAO.getInstance().selectLastInvoice(roomIdValue);
    }

    public static HashMap<String, String> addNewInvoice(HashMap<String, String> data) {
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> lastInvoice = Controller_Invoices.getLastInvoice(data.get("roomId"));

        if (lastInvoice != null) {
            if (Integer.parseInt(lastInvoice.get("paymentYear")) > LocalDateTime.now().getYear()
                    || (Integer.parseInt(lastInvoice.get("paymentYear")) == LocalDateTime.now().getYear()
                    && Integer.parseInt(lastInvoice.get("paymentMonth")) > LocalDateTime.now().getMonthValue())) {
                result.put("result", "0");
                result.put("message", "This room had an invoice on " + lastInvoice.get("paymentMonth") + "/" + lastInvoice.get("paymentYear"));
                return result;
            }
        }

        /*
            - Electric Cases:
                (1) Is A Family: Follow Electric Ranges Table.
                (2) Is Not A Family:
                    (2.1) Contract Time larger than 12 months & Has permanent address: Follow Electric Ranges Table.
                    (2.2) Contract Time under than 12 months:
                        (2.2.1) Unknown number of people: All Electricity Consumed with range "3".
                        (2.2.2) Specified number of people: 1 people is 1/4 range.
            - Water Cases:
                (1) At HCM city: m3/people/month
                (2) At others: m3/month
                --> Both cases follow Water Ranges Table.
         */

        ArrayList<WaterRangeModel> waterRanges = WaterRangeDAO.getInstance().selectAll();
        ArrayList<ElectricRangeModel> electricRanges = ElectricRangeDAO.getInstance().selectAll();

        // Check if data at Water Range, Electric Range is missing.
        if (waterRanges.size() == 0 || electricRanges.size() < 3
        || Controller_Electricity_Water.getLastWaterMaxRange() < Integer.MAX_VALUE
        || Controller_Electricity_Water.getLastElectricMaxRange() < Integer.MAX_VALUE) {
            result.put("result", "0");
            result.put("message", "It's Not Enough Data To Calculate Water and  Electric Price, please check Electric-Water");
            return result;
        }

        double total = STI(data.get("defaultRoomPrice"))
                + STI(data.get("garbage"))
                + STI(data.get("wifi"))
                + STI(data.get("vehicle"));

        int numberOfPeople = RoomDAO.getInstance()
                .selectById(data.get("roomId"))
                .getQuantity();

        ContractModel contract = ContractDAO.getInstance()
                .selectByCondition("WHERE roomId = \"" + data.get("roomId") + "\"")
                .get(0);

        int isFamily = Integer.parseInt(contract.getIsFamily());
        int isRegisteredPerAddress = Integer.parseInt(contract.getIsRegisteredPerAddress());
        int totalContractTimeAsMonth = contract.getTotalMonths();
        String region = RegionDAO.getInstance().selectAll().get(0).getRegion();

        int electricConsumed = STI(data.get("newElectricNumber")) - STI(data.get("formerElectricNumber"));
        int waterConsumed = (STI(data.get("newWaterNumber")) - STI(data.get("formerWaterNumber"))) / numberOfPeople;

        int electricPrice = 0;
        double totalWaterConsumed = 0;
        int environmentalFee = 150000;
        double electricTax = 8d;

        // Calculating Electric Price.
        if (electricConsumed != 0) {
            // Case (1) or (2.1)
            if (isFamily == 1 || (totalContractTimeAsMonth >= 12 && isRegisteredPerAddress == 1)) {
                for (ElectricRangeModel e : electricRanges) {
                    if (e.getMaxRangeValue() <= electricConsumed)
                        electricPrice += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                    else
                        electricPrice += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                }
            } else {
                // Case (2.2.1)
                if (numberOfPeople == -1) {
                    // Add Electric Consumption Price into Total with Range "3".
                    electricPrice += STI(data.get("newElectricNumber")) * electricRanges.get(2).getPrice()
                            - STI(data.get("formerElectricNumber")) * electricRanges.get(2).getPrice();
                }
                // Case (2.2.2)
                else {
                    for (ElectricRangeModel e : electricRanges) {
                        if (e.getMaxRangeValue() <= electricConsumed)
                            electricPrice += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                        else
                            electricPrice += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                    }
                    electricPrice = electricPrice * numberOfPeople / 4;
                }
            }
        }
        // 15% tax.
        total += electricPrice * electricTax / 100;

        // Calculating Water Price
        numberOfPeople = numberOfPeople == -1 ? 1 : numberOfPeople;
        if (waterConsumed != 0) {
            // Case (1)
            if (region.contains("Ho Chi Minh")) {
                double averageWaterConsumed = (double) waterConsumed / numberOfPeople;

                for (WaterRangeModel w : waterRanges) {
                    if (w.getMaxRangeValue() <= averageWaterConsumed)
                        totalWaterConsumed += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                    else
                        totalWaterConsumed += (averageWaterConsumed - w.getMinRangeValue()) * w.getPrice();
                }
                total += (int) (totalWaterConsumed * numberOfPeople);
            }
            // Case (2)
            else {
                for (WaterRangeModel w : waterRanges) {
                    if (w.getMaxRangeValue() <= waterConsumed)
                        totalWaterConsumed += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                    else
                        totalWaterConsumed += (waterConsumed - w.getMinRangeValue()) * w.getPrice();
                }
            }
        }
        // 150.000VNĐ Environmental Fee if Water Price >= 1.000.000VNĐ
        if (totalWaterConsumed >= 1000000)
            totalWaterConsumed += environmentalFee;

        total += totalWaterConsumed;

        String invoiceId = "I" + Configs.generateIdTail();
        LocalDateTime d = LocalDateTime.now();
        int addResult = InvoiceDAO.getInstance().insert(new String[]{
                invoiceId,
                data.get("roomId"),
                data.get("defaultRoomPrice"),
                d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear(),
                data.get("paymentYear"),
                data.get("paymentMonth"),
                data.get("formerElectricNumber"),
                data.get("newElectricNumber"),
                data.get("formerWaterNumber"),
                data.get("newWaterNumber"),
                data.get("garbage"),
                data.get("wifi"),
                data.get("vehicle"),
                Double.toString(total),
                "0"
        });
        if (addResult != 0) {
            result.put("result", "1");
            result.put("message", "Successfully Create Invoice of Room " + data.get("roomId") + ", Total is: " + total + "VNĐ");
        } else {
            result.put("result", "0");
            result.put("message", "Something went wrong with your Database");
        }
        return result;
    }

    public static String[][] getAllInvoicesWithTableFormat() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();
        String[][] rooms = new String[result.size()][5];
//        for (int i = 0; i < result.size(); i++) {
//            rooms[i][0] = result.get(i).getRoomId();
//            rooms[i][1] = Integer.toString(result.get(i).getQuantity());
//            rooms[i][2] = Integer.toString(result.get(i).getMaxQuantity());
//            rooms[i][3] = Integer.toString(result.get(i).getDefaultRoomPrice());
//            rooms[i][4] = "Delete";
//        }
        return rooms;
    }

    public static int STI(String num) {
        return Integer.parseInt(num);
    }
}

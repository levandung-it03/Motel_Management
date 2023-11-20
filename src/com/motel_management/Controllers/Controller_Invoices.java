package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.*;
import com.motel_management.Views.Configs;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_Invoices {
    public Controller_Invoices() { super(); }

    public static InvoiceModel getLastInvoice(String roomIdValue) {
        ArrayList<InvoiceModel> result = InvoiceDAO.getInstance()
                .selectByCondition("WHERE roomId=\"" + roomIdValue + "\"" + "ORDER BY paymentYear DESC, paymentMonth DESC");
        if (result.size() == 0)
            return null;
        return result.get(0);
    }

    public static int addNewInvoice(HashMap<String, String> data) {
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

        // Check is data at Water Range, Electric Range is missing.
        if (waterRanges.size() * electricRanges.size() == 0) {
            int total = STI(data.get("defaultRoomPrice"))
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

            int electricConsumed = STI(data.get("newElectricNumber"))- STI(data.get("formerElectricNumber"));
            int waterConsumed = (STI(data.get("newWaterNumber")) - STI(data.get("formerWaterNumber"))) / numberOfPeople;

            // Calculating Electric Price.
            if (electricConsumed != 0) {

                // Case (1) or (2.1)
                if (isFamily == 1 || (totalContractTimeAsMonth >= 12 && isRegisteredPerAddress == 1)) {
                    for (ElectricRangeModel e : electricRanges) {
                        if (e.getMaxRangeValue() <= electricConsumed)
                            total += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                        else
                            total += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                    }
                } else {
                    // Case (2.2.1)
                    if (numberOfPeople == -1) {
                        ArrayList<RoomModel> rooms = RoomDAO.getInstance().selectByCondition("WHERE quantity > 0");
                        numberOfPeople = rooms.stream()
                                .mapToInt(RoomModel::getQuantity)
                                .sum() / rooms.size();
                        if (numberOfPeople == 0) { numberOfPeople = 1; }

                        // Add Electric Consumption Price into Total with Range "3".
                        total += STI(data.get("newElectricNumber")) * electricRanges.get(2).getPrice()
                                - STI(data.get("formerElectricNumber")) * electricRanges.get(2).getPrice();
                    }
                    // Case (2.2.2)
                    else {
                        int electricPrice = 0;
                        for (ElectricRangeModel e : electricRanges) {
                            if (e.getMaxRangeValue() <= electricConsumed)
                                electricPrice += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                            else
                                electricPrice += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                        }
                        electricPrice = electricPrice * numberOfPeople / 4;
                        total += electricPrice;
                    }
                }
            }

            // Calculating Water Price
            if (waterConsumed != 0) {
                // Case (1)
                if (region.contains("Ho Chi Minh")) {
                    double totalWaterConsumed = 0;
                    double averageWaterConsumed = (double) waterConsumed / numberOfPeople;

                    for (WaterRangeModel w: waterRanges) {
                        if (w.getMaxRangeValue() <= averageWaterConsumed)
                            totalWaterConsumed += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                        else
                            totalWaterConsumed += (averageWaterConsumed - w.getMinRangeValue()) * w.getPrice();
                    }
                    total += (int) (totalWaterConsumed * numberOfPeople);
                }
                // Case (2)
                else {
                    for (WaterRangeModel w: waterRanges) {
                        if (w.getMaxRangeValue() <= waterConsumed)
                            total += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                        else
                            total += (waterConsumed - w.getMinRangeValue()) * w.getPrice();
                    }
                }
            }
            System.out.println(total);
            String invoiceId = "I" + Configs.generateIdTail();
            LocalDateTime d = LocalDateTime.now();
            return InvoiceDAO.getInstance().insert(new String[] {
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
                    Integer.toString(total),
                    "0"
            });
        } else {
            return 0;
        }
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

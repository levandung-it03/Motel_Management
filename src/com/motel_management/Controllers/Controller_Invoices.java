package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.*;
import com.motel_management.Configs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Controller_Invoices {

    public Controller_Invoices() {
        super();
    }

    public static HashMap<String, String> getLastInvoice(String roomIdValue) {
        return InvoiceDAO.getInstance().selectLastInvoice(roomIdValue);
    }

    public static InvoiceModel getInvoiceByInvoiceId(String id) {
        return InvoiceDAO.getInstance().selectById(id);
    }

    public static HashMap<String, String> addNewInvoice(HashMap<String, String> data) {
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> lastInvoice = Controller_Invoices.getLastInvoice(data.get("roomId"));

        // Creating Invoice before the Last Invoice Created Date.
        if (lastInvoice != null) {
            if (STI(lastInvoice.get("paymentYear")) > LocalDateTime.now().getYear()
                    || (STI(lastInvoice.get("paymentYear")) == LocalDateTime.now().getYear()
                    && STI(lastInvoice.get("paymentMonth")) > LocalDateTime.now().getMonthValue())) {
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

        ArrayList<WaterRangeModel> waterRanges = WaterRangeDAO.getInstance().selectByCondition("ORDER BY maxRangeValue ASC");
        ArrayList<ElectricRangeModel> electricRanges = ElectricRangeDAO.getInstance().selectByCondition("ORDER BY maxRangeValue ASC");

        // Check if data at Water Range, Electric Range is missing.
        if (waterRanges.isEmpty() || electricRanges.size() < 3
                || waterRanges.getLast().getMaxRangeValue() < Integer.MAX_VALUE
                || electricRanges.getLast().getMaxRangeValue() < Integer.MAX_VALUE) {
            result.put("result", "0");
            result.put("message", "It's Not Enough Data To Calculate Water and  Electric Price, please check Electric-Water");
            return result;
        }

        int total = STI(data.get("defaultRoomPrice"))
                + STI(data.get("garbage"))
                + STI(data.get("wifi"))
                + STI(data.get("vehicle"));

        int numberOfPeople = RoomDAO.getInstance()
                .selectById(data.get("roomId"))
                .getQuantity();

        ContractModel contract = ContractDAO.getInstance().selectLastContractByRoomId(data.get("roomId"));

        // Creating Invoice before Contract's startingDate.
        if (STI(data.get("paymentYear")) < contract.getStartingDate().toLocalDate().getYear()
                || (STI(data.get("paymentYear")) == contract.getStartingDate().toLocalDate().getYear()
                && STI(data.get("paymentMonth")) < contract.getStartingDate().toLocalDate().getMonthValue())) {
            result.put("result", "0");
            result.put("message", "You can't create Invoice before the Starting Date of Contract!");
            return result;
        }


        boolean isFamily = contract.getIsFamily();
        boolean isRegisteredPerAddress = contract.getIsRegisteredPerAddress();
        long totalContractTimeAsMonth = Period.between(
                contract.getStartingDate().toLocalDate(),
                contract.getEndingDate().toLocalDate()
        ).toTotalMonths();
        String region = RegionDAO.getInstance().selectAll().getFirst().getRegion();

        int electricConsumed = STI(data.get("newElectricNumber")) - STI(data.get("formerElectricNumber"));
        int waterConsumed = STI(data.get("newWaterNumber")) - STI(data.get("formerWaterNumber"));

        int electricPrice = 0;
        double waterPrice = 0;
        int environmentalFee = 0;
        double electricTax = 0;

        // Calculating Electric Price.
        if (electricConsumed != 0) {
            // Case (1) or (2.1)
            if (isFamily || (totalContractTimeAsMonth >= 12 && isRegisteredPerAddress)) {
                for (ElectricRangeModel e : electricRanges) {
                    if (e.getMaxRangeValue() <= electricConsumed)
                        electricPrice += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                    else {
                        electricPrice += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                        break;
                    }
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
                        else {
                            electricPrice += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
                            break;
                        }
                    }
                    electricPrice = electricPrice * numberOfPeople / 4;
                }
            }
        }

        // Tax.
        electricPrice = (int) (electricPrice * (100 + electricTax) / 100);
        total += electricPrice;

        // Calculating Water Price
        numberOfPeople = (numberOfPeople == -1) ? 1 : numberOfPeople;
        if (waterConsumed != 0) {
            // Case (1)
            if (region.contains("Ho Chi Minh")) {
                double averageWaterConsumed = (double) waterConsumed / numberOfPeople;
                for (WaterRangeModel w : waterRanges) {
                    if (w.getMaxRangeValue() <= averageWaterConsumed)
                        waterPrice += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                    else {
                        waterPrice += (averageWaterConsumed - w.getMinRangeValue()) * w.getPrice();
                        break;
                    }
                }
                waterPrice *= numberOfPeople;
            }
            // Case (2)
            else {
                for (WaterRangeModel w : waterRanges) {
                    if (w.getMaxRangeValue() <= waterConsumed)
                        waterPrice += (w.getMaxRangeValue() - w.getMinRangeValue()) * w.getPrice();
                    else {
                        waterPrice += (waterConsumed - w.getMinRangeValue()) * w.getPrice();
                        break;
                    }
                }
            }
        }

        // 150.000VNĐ Environmental Fee if Water Price >= 1.000.000VNĐ
        if (waterPrice >= 1000000)
            waterPrice += environmentalFee;
        total += (int) waterPrice;

        String invoiceId = "I" + Configs.generateIdTail();
        LocalDateTime d = LocalDateTime.now();
        String priceRaisedDate = Configs.simpleDateFormat.format(RoomPriceHistoryDAO.getInstance()
                .selectCurrentRoomPriceHistoryWithRoomId(data.get("roomId")).getPriceRaisedDate());

        int addResult = InvoiceDAO.getInstance().insert(new String[]{
                invoiceId,
                data.get("roomId"),
                priceRaisedDate,
                d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear(),
                data.get("paymentYear"),
                data.get("paymentMonth"),
                data.get("formerElectricNumber"),
                data.get("newElectricNumber"),
                data.get("formerWaterNumber"),
                data.get("newWaterNumber"),
                Integer.toString(electricPrice),
                Integer.toString((int) waterPrice),
                data.get("garbage"),
                data.get("wifi"),
                data.get("vehicle"),
                "0"
        });

        if (addResult != 0) {
            result.put("result", "1");
            result.put("message", "Successfully Create Invoice of Room " + data.get("roomId") + ", Total is: " + total + "VNĐ");
        } else {
            result.put("result", "0");
            result.put("message", "This Room Already Had Invoice On " + data.get("paymentMonth") + "/" + data.get("paymentYear"));
        }
        return result;
    }

    public static ArrayList<String[]> getAllLastInvoicesOfRoomWithTableFormat() {
        ArrayList<String[]> result = new ArrayList<>();

        ArrayList<InvoiceModel> newestInvoices = InvoiceDAO.getInstance().selectByCondition("""
                WHERE (roomId, paymentYear, paymentMonth) IN (
                	SELECT roomId, MAX(paymentYear), paymentMonth FROM Invoice
                	GROUP BY roomId
                    ORDER BY paymentMonth DESC
                )
                ORDER BY roomId ASC
        """);
        for (InvoiceModel invoice : newestInvoices) {
            String[] eachTempResult = new String[9];
            eachTempResult[0] = invoice.getRoomId();
            eachTempResult[1] = "View All";
            eachTempResult[2] = invoice.getInvoiceId();
            eachTempResult[3] = Integer.toString(invoice.getPaymentMonth());
            eachTempResult[4] = Integer.toString(invoice.getPaymentYear());
            eachTempResult[5] = Configs.simpleDateFormat.format(invoice.getDateCreated());
            eachTempResult[6] = Configs.convertStringToVNDCurrency(invoice.getGarbage()
                    + invoice.getWaterPrice()
                    + invoice.getElectricPrice()
                    + invoice.getWifi()
                    + invoice.getVehicle()
                    + RoomPriceHistoryDAO.getInstance()
                        .selectById(invoice.getRoomId(), invoice.getPriceRaisedDate())
                        .getRoomPrice()
            );
            eachTempResult[7] = invoice.getWasPaid() ? "YES" : "NO";
            eachTempResult[8] = "Delete";
            result.add(eachTempResult);
        }
        return result;
    }

    public static HashMap<String, String> updateInvoiceStatus(String invoiceId) {
        HashMap<String, String> result = new HashMap<>();
        InvoiceModel invoice = InvoiceDAO.getInstance().selectById(invoiceId);
        invoice.setWasPaid(!invoice.getWasPaid());
        if (InvoiceDAO.getInstance().update(invoice) == 1) {
            result.put("result", "1");
            result.put("message", "Change Invoice Payment Status Successfully!");
        } else {
            result.put("result", "0");
            result.put("message", "Change Invoice Payment Status Failed!");
        }
        return result;
    }

    public static ArrayList<InvoiceModel> getInvoicesByRoomIdWithPage(int currentPage, String roomId) {
        return InvoiceDAO.getInstance()
                .selectByCondition(
                        "WHERE roomId=\"" + roomId + "\" " +
                                "ORDER BY paymentYear DESC, paymentMonth DESC " +
                                "LIMIT " + (12 * currentPage) + ", 12"
                );
    }

    public static int deleteInvoice(String invoiceId) {
        InvoiceModel invoice = InvoiceDAO.getInstance().selectById(invoiceId);
        if (!invoice.getWasPaid()){
            return InvoiceDAO.getInstance().delete(invoiceId);
        }
        return 0;
    }

    public static int STI(String num) {
        return Integer.parseInt(num);
    }

}
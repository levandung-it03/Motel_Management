package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Models.WaterRangeModel;
import com.motel_management.Views.Configs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller_Invoices {
    public Controller_Invoices() {}

    public static ArrayList<InvoiceModel> getInvoiceWithCondition(String condition) {
        return InvoiceDAO.getInstance().selectByCondition(condition);
    }

    public static int addNewInvoice(HashMap<String, String> data) {
        ArrayList<WaterRangeModel> waterRanges = WaterRangeDAO.getInstance().selectAll();
        ArrayList<ElectricRangeModel> electricRanges = ElectricRangeDAO.getInstance().selectAll();
        int total = STI(data.get("defaultRoomPrice")) + STI(data.get("garbage")) + STI(data.get("wifi"))
                + STI(data.get("vehicle"));
        int averagePeople = RoomDAO.getInstance().selectById(data.get("roomId")).getQuantity();

        // Room with unknown quantity
        if (averagePeople == -1) {
            ArrayList<RoomModel> rooms = RoomDAO.getInstance().selectByCondition("WHERE quantity > 0");

            averagePeople = rooms.stream().mapToInt(RoomModel::getQuantity).sum() / rooms.size();
            if (averagePeople == 0)     { averagePeople = 1; }

            // Add Electric Consumption Price into Total.
            total += (STI(data.get("newElectricNumber")) - STI(data.get("formerElectricNumber"))) * electricRanges.get(2).getPrice();
        }
        // Room with specified quantity
        else {
            // Add Electric Consumption Price into Total.
            int electricConsumed = STI(data.get("newElectricNumber")) - STI(data.get("formerElectricNumber"));
            for (ElectricRangeModel e: electricRanges) {
                if (e.getMaxRangeValue() <= electricConsumed)
                    total += (e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice();
                else
                    total += (electricConsumed - e.getMinRangeValue() + 1) * e.getPrice();
            }
        }

        // Add Water Consumption Price into Total.
        double totalWaterConsumed = 0;
        int waterConsumed = (STI(data.get("newWaterNumber")) - STI(data.get("formerWaterNumber"))) / averagePeople;
        for (WaterRangeModel w: waterRanges) {
            if (w.getMaxRangeValue() <= waterConsumed)
                totalWaterConsumed += (w.getMaxRangeValue() - w.getMinRangeValue() + 1) * w.getPrice();
            else
                totalWaterConsumed += (waterConsumed - w.getMinRangeValue() + 1) * w.getPrice();
        }
        total += (int) (totalWaterConsumed * averagePeople);

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

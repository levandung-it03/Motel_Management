package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;
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
        String invoiceId = "I" + Configs.generateIdTail();
        LocalDateTime d = LocalDateTime.now();
        String nowStrDate = d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear();
        AtomicInteger total = new AtomicInteger(
                STI(data.get("defaultRoomPrice"))
                + STI(data.get("garbage"))
                + STI(data.get("wifi"))
                + STI(data.get("vehicle"))
        );

        int electricConsumed = STI(data.get("newElectricNumber")) - STI(data.get("formerElectricNumber"));
        ElectricRangeDAO.getInstance().selectAll().forEach(e -> {
            if (e.getMaxRangeValue() <= electricConsumed)
                total.addAndGet((e.getMaxRangeValue() - e.getMinRangeValue() + 1) * e.getPrice());
            else
                total.addAndGet((electricConsumed - e.getMinRangeValue() + 1) * e.getPrice());
        });

        int waterConsumed = STI(data.get("newWaterNumber")) - STI(data.get("formerWaterNumber"));
        WaterRangeDAO.getInstance().selectAll().forEach(w -> {
            if (w.getMaxRangeValue() <= waterConsumed)
                total.addAndGet((w.getMaxRangeValue() - w.getMinRangeValue() + 1) * w.getPrice());
            else
                total.addAndGet((waterConsumed - w.getMinRangeValue() + 1) * w.getPrice());
        });

        return InvoiceDAO.getInstance().insert(new String[] {
                invoiceId,
                data.get("roomId"),
                data.get("defaultRoomPrice"),
                nowStrDate,
                data.get("paymentYear"),
                data.get("paymentMonth"),
                data.get("formerElectricNumber"),
                data.get("newElectricNumber"),
                data.get("formerWaterNumber"),
                data.get("newWaterNumber"),
                data.get("garbage"),
                data.get("wifi"),
                data.get("vehicle"),
                Integer.toString(total.get()),
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

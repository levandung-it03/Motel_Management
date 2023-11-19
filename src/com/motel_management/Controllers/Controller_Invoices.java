package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;

import java.util.ArrayList;

public class Controller_Invoices {
    public Controller_Invoices() {}

    public static ArrayList<InvoiceModel> getInvoiceWithCondition(String condition) {
        return InvoiceDAO.getInstance().selectByCondition(condition);
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
}

package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.*;
import com.motel_management.Views.Configs;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller_Statistic {
    public Controller_Statistic() { super(); }

    public static int getTotalRoom() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();
        return result.size();
    }
    public static int getTotalPerson() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectByCondition("WHERE quantity > 0");
        int totalPerson=0;
        for (RoomModel roomModel : result) {
            totalPerson += roomModel.getQuantity();
        }
        return totalPerson;
    }
    public static int getTotalAccount() {
        ArrayList<AccountModel> result = AccountDAO.getInstance().selectAll();
        return result.size();
    }
    public static int getTotalRevenue() {
        ArrayList<InvoiceModel> result = InvoiceDAO.getInstance().selectByCondition("WHERE paymentYear = \""+
                LocalDate.now().getYear() +"\" AND wasPaid = 1");
        int totalYearRevenue =0;
        for (InvoiceModel invoiceModel : result) {
            totalYearRevenue += invoiceModel.getTotal();
        }
        return totalYearRevenue;
    }

    public static Object[][] getRoomList() {
        ArrayList<PersonModel> result = PersonDAO.getInstance().selectAll();
        Object[][] rooms = new Object[result.size()][4];
        for (int i = 0; i < result.size(); i++) {
            RoomModel roomResult = RoomDAO.getInstance().selectById(result.get(i).getRoomId());
            rooms[i][0] = result.get(i).getRoomId();
            rooms[i][1] = result.get(i).getLastName()+ " " +result.get(i).getFirstName();
            rooms[i][2] = roomResult.getQuantity();
            rooms[i][3] = Configs.convertStringToVNDCurrency(roomResult.getDefaultRoomPrice());
        }
        return rooms;
    }
    public static Object[][] getRevenue(int year) {
        Object[][] revenue = new Object[13][2];
        int totalYear =0;
        for (int i = 0; i < 12; i++) {
            ArrayList<InvoiceModel> result = InvoiceDAO.getInstance().selectByCondition("WHERE paymentMonth = \""+
                    (i+1)+"\" AND paymentYear = \""+year+"\" AND wasPaid = 1");
            int totalMonth=0;
            for (InvoiceModel invoiceModel : result) {
                totalMonth += invoiceModel.getTotal();
            }
            revenue[i][0] = i+1;
            revenue[i][1] = Configs.convertStringToVNDCurrency(String.valueOf(totalMonth));
            totalYear+=totalMonth;
        }
        revenue[12][0] = "Total";
        revenue[12][1] = Configs.convertStringToVNDCurrency(String.valueOf(totalYear));
        return revenue;
    }
}

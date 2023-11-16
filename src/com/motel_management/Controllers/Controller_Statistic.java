package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.AccountModel;
import com.motel_management.Models.RoomModel;

import java.util.ArrayList;

public class Controller_Statistic {
    public Controller_Statistic() {}

    public static int getTotalRoom() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();
        return result.size();
    }
    public static int getTotalPerson() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();
        int totalPerson=0;
        for (int i=0;i< result.size();i++){
            totalPerson+= result.get(i).getQuantity();
        }
        return totalPerson;
    }
    public static int getTotalAccount() {
        ArrayList<AccountModel> result = AccountDAO.getInstance().selectAll();
        return result.size();
    }

}

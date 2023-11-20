package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.AccountModel;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;

import java.util.ArrayList;

public class Controller_Statistic {
    public Controller_Statistic() { super(); }

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

    public static String[][] getRoomList() {
        ArrayList<PersonModel> result = PersonDAO.getInstance().selectAll();
        String[][] rooms = new String[result.size()][4];
        for (int i = 0; i < result.size(); i++) {
            RoomModel roomResult = RoomDAO.getInstance().selectById(result.get(i).getRoomId());
            rooms[i][0] = result.get(i).getRoomId();
            rooms[i][1] = result.get(i).getLastName()+ " " +result.get(i).getFirstName();
            rooms[i][2] = Integer.toString(roomResult.getQuantity());
            rooms[i][3] = Integer.toString(roomResult.getDefaultRoomPrice());
        }
        return rooms;
    }
}

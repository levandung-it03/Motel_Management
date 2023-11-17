package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;

import java.util.HashMap;

public class Controller_Contract {
    public Controller_Contract() {}

    public static int addNewContract(HashMap<String, String> data) {
        String[] contractData = new String[];
        int res1 = ContractDAO.getInstance().insert();
        int res2 = PersonDAO.getInstance().insert();
        int res3 =
        return res;
    }
}

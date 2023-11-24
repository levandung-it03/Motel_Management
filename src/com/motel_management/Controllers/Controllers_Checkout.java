package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.CheckOutDAO;
import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.CheckOutModel;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;

import java.util.ArrayList;

public class Controllers_Checkout {
    public Controllers_Checkout() {
        super();
    }
    public static String addCheckOutHistory(String[] data){
        int res = CheckOutDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            return "CK" + Configs.generateIdTail();
        }
    }
    public static String[][] getCheckOutHistory() {
        ArrayList<CheckOutModel> result = CheckOutDAO.getInstance().selectAll();
        String[][] checkouts = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            checkouts[i][0] = result.get(i).getCheckOutId();
            checkouts[i][1] = result.get(i).getContractId();
        }
        return checkouts;
    }
}

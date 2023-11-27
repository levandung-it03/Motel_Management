package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.CheckOutDAO;
import com.motel_management.Models.CheckOutModel;
import com.motel_management.Views.Configs;

import java.text.SimpleDateFormat;
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
    public static Object[][] getCheckOutHistory() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<CheckOutModel> result = CheckOutDAO.getInstance().selectAll();
        Object[][] checkouts = new Object[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            checkouts[i][0] = result.get(i).getCheckOutId();
            checkouts[i][1] = result.get(i).getContractId();
            checkouts[i][2] = dateFormat.format(result.get(i).getCheckOutDate());
            checkouts[i][3] = result.get(i).getReason();
        }
        return checkouts;
    }
}

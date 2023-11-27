package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.CheckOutDAO;
import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.CheckOutModel;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_Checkout {
    public Controller_Checkout() {
        super();
    }

    public static String addCheckOutHistory(String[] data) {
        int res = CheckOutDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            return "CK" + Configs.generateIdTail();
        }
    }

    public static Object[][] getAllCheckOutByYearWithTableFormat(String year) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<CheckOutModel> selectedContracts = CheckOutDAO.getInstance()
                .selectByCondition("WHERE YEAR(checkOutDate)=\"" + year + "\"");

        Object[][] checkouts = new Object[selectedContracts.size()][6];
        for (int i = 0; i < selectedContracts.size(); i++) {
            checkouts[i][0] = selectedContracts.get(i).getCheckOutId();
            checkouts[i][1] = selectedContracts.get(i).getContractId();
            checkouts[i][2] = sdf.format(selectedContracts.get(i).getCheckOutDate());
            checkouts[i][3] = selectedContracts.get(i).getReason();
        }
        return checkouts;
    }
}
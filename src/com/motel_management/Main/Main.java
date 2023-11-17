package com.motel_management.Main;

import com.motel_management.DataAccessObject.DAOInterface;
import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Views.*;

public class Main {
    public static void main(String[] args) {
        // Starting our application.
//        Application.createApplication();
        WaterRangeDAO.getInstance().insert(new String[] {"W01", "Muc 1", "50", "1000"});

    }
}
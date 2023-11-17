package com.motel_management.Controllers;
import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Models.WaterRangeModel;

import java.util.ArrayList;

public class Controller_Electricity_Water {
    public Controller_Electricity_Water() {
    }

    public static int deleteElectricById(String id) {
        return ElectricRangeDAO.getInstance().delete(id);
    }
    public static int deleteWaterById(String id) { return WaterRangeDAO.getInstance().delete(id); }

    public static String[][] getElectricList() {
        ArrayList<ElectricRangeModel> result = ElectricRangeDAO.getInstance().selectAll();
        String[][] electrics = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            electrics[i][0] = result.get(i).getRangeName();
            electrics[i][1] = Integer.toString(result.get(i).getMinRangeValue());
            electrics[i][2] = Integer.toString(result.get(i).getMaxRangeValue());
            electrics[i][3] = Integer.toString(result.get(i).getPrice());
            electrics[i][4] = "Delete";
        }
        return electrics;
    }
    public static String[][] getWaterList() {
        ArrayList<ElectricRangeModel> result = ElectricRangeDAO.getInstance().selectAll();
        String[][] waters = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            waters[i][0] = result.get(i).getRangeName();
            waters[i][1] = Integer.toString(result.get(i).getMinRangeValue());
            waters[i][2] = Integer.toString(result.get(i).getMaxRangeValue());
            waters[i][3] = Integer.toString(result.get(i).getPrice());
            waters[i][4] = "Delete";
        }
        return waters;
    }
    public static String getElectricLastId() {
        ArrayList<ElectricRangeModel> roomList = ElectricRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");
        int id =1;
        if (roomList.isEmpty()) {
            return "E"+String.format("%03d", id);
        } else {
            id++;
            return "E"+String.format("%03d", id);
        }
    }
    public static String getWaterLastId() {
        ArrayList<WaterRangeModel> roomList = WaterRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");
        int id =1;
        if (roomList.isEmpty()) {
            return "W"+String.format("%03d", id);
        } else {
            id++;
            return "W"+String.format("%03d", id);
        }
    }
}

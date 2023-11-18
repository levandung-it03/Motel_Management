package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.WaterRangeModel;

import java.util.ArrayList;

public class Controller_Electricity_Water {
    public Controller_Electricity_Water() {
    }

    public static int deleteElectricById(String id) {
        return ElectricRangeDAO.getInstance().delete(id);
    }

    public static int deleteWaterById(String id) {
        return WaterRangeDAO.getInstance().delete(id);
    }

    public static String[][] getElectricList() {
        ArrayList<ElectricRangeModel> result = ElectricRangeDAO.getInstance().selectAll();
        String[][] electrics = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            electrics[i][0] = result.get(i).getRangeId();
            electrics[i][1] = result.get(i).getRangeName();
            electrics[i][2] = Integer.toString(result.get(i).getMinRangeValue());
            electrics[i][3] = Integer.toString(result.get(i).getMaxRangeValue());
            electrics[i][4] = Integer.toString(result.get(i).getPrice());
            electrics[i][5] = "Delete";
        }
        return electrics;
    }

    public static String[][] getWaterList() {
        ArrayList<WaterRangeModel> result = WaterRangeDAO.getInstance().selectAll();
        String[][] waters = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            waters[i][0] = result.get(i).getRangeId();
            waters[i][1] = result.get(i).getRangeName();
            waters[i][2] = Integer.toString(result.get(i).getMinRangeValue());
            waters[i][3] = Integer.toString(result.get(i).getMaxRangeValue());
            waters[i][4] = Integer.toString(result.get(i).getPrice());
            waters[i][5] = "Delete";
        }
        return waters;
    }

    public static String getElectricLastId() {
        ArrayList<ElectricRangeModel> electricList = ElectricRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");
        int electricId = 1;
        if (electricList.isEmpty()) {
            return "E" + String.format("%03d", electricId);
        } else {
            electricId++;
            return "E" + String.format("%03d", electricId);
        }
    }

    public static String getWaterLastId() {
        ArrayList<WaterRangeModel> waterList = WaterRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");

        if (waterList.isEmpty()) {
            return "W" + String.format("%03d", 1);
        } else {
            int id = Integer.parseInt(waterList.get(waterList.size()-1).getRangeId()) + 1;
            return "W" + String.format("%03d", id);
        }
    }

    public static String addNewElectric(String[] data) {
        int res = ElectricRangeDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            ArrayList<ElectricRangeModel> electricList = ElectricRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");
            int id = 1;
            if (electricList.isEmpty()) {
                return "E" + String.format("%03d", id);
            } else {
                id++;
                return "E" + String.format("%03d", id);
            }
        }
    }

    public static String addNewWater(String[] data) {
        int res = WaterRangeDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            ArrayList<WaterRangeModel> electricList = WaterRangeDAO.getInstance().selectByCondition("ORDER BY rangeId ASC");
            int id = 1;
            if (electricList.isEmpty()) {
                return "W" + String.format("%03d", id);
            } else {
                id++;
                return "W" + String.format("%03d", id);
            }
        }
    }
}

package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.RegionModel;
import com.motel_management.Models.WaterRangeModel;
import com.motel_management.Configs;

import java.util.ArrayList;

public class Controller_Electricity_Water {
    public Controller_Electricity_Water() { super(); }

    public static int deleteElectricById(String id) {
        return ElectricRangeDAO.getInstance().delete(id);
    }

    public static int deleteWaterById(String id) {
        return WaterRangeDAO.getInstance().delete(id);
    }

    public static Object[][] getElectricList() {
        ArrayList<ElectricRangeModel> result = ElectricRangeDAO.getInstance().selectByCondition("ORDER by minRangeValue");
        Object[][] electrics = new Object[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            electrics[i][0] = result.get(i).getRangeId();
            electrics[i][1] = result.get(i).getRangeName();
            electrics[i][2] = result.get(i).getMinRangeValue();
            electrics[i][3] = result.get(i).getMaxRangeValue();
            electrics[i][4] = result.get(i).getPrice();
            electrics[i][5] = "Delete";
        }
        return electrics;
    }

    public static Object[][] getWaterList() {
        ArrayList<WaterRangeModel> result = WaterRangeDAO.getInstance().selectByCondition("ORDER by minRangeValue");
        Object[][] waters = new Object[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            waters[i][0] = result.get(i).getRangeId();
            waters[i][1] = result.get(i).getRangeName();
            waters[i][2] = result.get(i).getMinRangeValue();
            waters[i][3] = result.get(i).getMaxRangeValue();
            waters[i][4] = result.get(i).getPrice();
            waters[i][5] = "Delete";
        }
        return waters;
    }

    public static int getLastElectricMaxRange() {
        ArrayList<ElectricRangeModel> electricList = ElectricRangeDAO.getInstance().selectByCondition("ORDER BY maxRangeValue ASC");
        if (electricList.isEmpty()) {
            return 0;
        } else {
            return electricList.get(electricList.size() - 1).getMaxRangeValue();
        }
    }

    public static int getLastWaterMaxRange() {
        ArrayList<WaterRangeModel> waterList = WaterRangeDAO.getInstance().selectByCondition("ORDER BY maxRangeValue ASC");
        if (waterList.isEmpty()) {
            return 0;
        } else {
            return waterList.get(waterList.size() - 1).getMaxRangeValue();
        }
    }

    public static String checkRegion() {
        ArrayList<RegionModel> region = RegionDAO.getInstance().selectAll();
        if (!region.isEmpty()) {
            if (region.get(0).getRegion().equalsIgnoreCase("Tp.Ho Chi Minh")) {
                return "Price(VND/Person/m³)";
            } else {
                return "Price(VND/m³)";
            }
        }
        return "";
    }

    public static String addNewElectric(String[] data) {
        int res = ElectricRangeDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            return "E" + Configs.generateIdTail();
        }
    }

    public static String addNewWater(String[] data) {
        int res = WaterRangeDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            return "W" + Configs.generateIdTail();
        }
    }

    public static int updateElectric(String[] data) {
        return ElectricRangeDAO.getInstance().update(data);
    }

    public static int updateWater(String[] data) {
        return WaterRangeDAO.getInstance().update(data);
    }
}

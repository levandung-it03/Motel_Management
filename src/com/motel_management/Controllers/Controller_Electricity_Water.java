package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.DataAccessObject.WaterRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.RegionModel;
import com.motel_management.Models.WaterRangeModel;
import com.motel_management.Views.Configs;

import java.util.ArrayList;

public class Controller_Electricity_Water {
    public Controller_Electricity_Water() { super(); }

    public static int deleteElectricById(String id) {
        return ElectricRangeDAO.getInstance().delete(id);
    }

    public static int deleteWaterById(String id) {
        return WaterRangeDAO.getInstance().delete(id);
    }

    public static String[][] getElectricList() {
        ArrayList<ElectricRangeModel> result = ElectricRangeDAO.getInstance().selectByCondition("ORDER by minRangeValue");
        String[][] electrics = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            electrics[i][0] = result.get(i).getRangeId();
            electrics[i][1] = result.get(i).getRangeName();
            electrics[i][2] = Integer.toString(result.get(i).getMinRangeValue());
            electrics[i][3] = Integer.toString(result.get(i).getMaxRangeValue());
            if(result.get(i).getMaxRangeValue() == Integer.MAX_VALUE){
                electrics[i][3] = "Unlimited";
            }else{
                electrics[i][3] = Integer.toString(result.get(i).getMaxRangeValue());
            }
            electrics[i][4] = Integer.toString(result.get(i).getPrice());
            electrics[i][5] = "Delete";
        }
        return electrics;
    }

    public static String[][] getWaterList() {
        ArrayList<WaterRangeModel> result = WaterRangeDAO.getInstance().selectByCondition("ORDER by minRangeValue");
        String[][] waters = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            waters[i][0] = result.get(i).getRangeId();
            waters[i][1] = result.get(i).getRangeName();
            waters[i][2] = Integer.toString(result.get(i).getMinRangeValue());
            if(result.get(i).getMaxRangeValue() == Integer.MAX_VALUE){
                waters[i][3] = "Unlimited";
            }else{
                waters[i][3] = Integer.toString(result.get(i).getMaxRangeValue());
            }
            waters[i][4] = Integer.toString(result.get(i).getPrice());
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
        if (region.get(0).getRegion().equalsIgnoreCase("Tp.Ho Chi Minh")) {
            return "Price(VND/Person/m³)";
        } else {
            return "Price(VND/m³)";
        }
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

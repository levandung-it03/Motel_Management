package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.Models.RegionModel;
import com.motel_management.Views.Configs;

import java.util.ArrayList;

public class Controller_ChooseRegion {

    // Constructor
    public Controller_ChooseRegion() { super(); }

    public static int setNewRegion(String region) {
        String id = "R" + Configs.generateIdTail();
        return RegionDAO.getInstance().insert(new String[] {id, region});
    }

    public static String checkIfRegionExisted() {
        ArrayList<RegionModel> currentRegion = RegionDAO.getInstance().selectAll();
        return currentRegion.size() == 0 ? null : currentRegion.get(0).getRegion();
    }

    public static int changeRegion(String region, String newRegion) {
        RegionModel regionObj = RegionDAO.getInstance().selectByCondition("WHERE region=\"" + region + "\"").get(0);
        regionObj.setRegion(newRegion);
        return RegionDAO.getInstance().update(regionObj);
    }
}

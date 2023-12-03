package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.Models.RegionModel;

import java.util.ArrayList;

public class Controller_Header {
    public static void deleteRegion(String currentRegion){
        ArrayList<RegionModel> res = RegionDAO.getInstance().selectByCondition("WHERE region LIKE \"%" + currentRegion + "%\"");
        RegionModel temp = res.get(0);
        RegionDAO.getInstance().delete(temp.getRegionId());
    }
}

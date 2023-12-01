package com.motel_management.Views.Listeners;

import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.Models.RegionModel;
import com.motel_management.Views.Graphics.Frame_Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Listeners_Header {
    public static ActionListener resetRegionAction(JFrame mainFrameApp, String currentRegion) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RegionModel> res = RegionDAO.getInstance().selectByCondition("WHERE region LIKE \"%" + currentRegion + "%\"");
                RegionModel temp = res.get(0);
                RegionDAO.getInstance().delete(temp.getRegionId());

                for (RegionModel re : res) {
                    System.out.println(re.getRegionId());
                    RegionDAO.getInstance().delete(re.getRegionId());
                }
                mainFrameApp.dispose();
                Frame_Login loginFrame = new Frame_Login();
            }
        };

    }

    public static ActionListener logOutAction(JFrame mainFrameApp) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(new JPanel(), "Are you sure ?", "Notice",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    mainFrameApp.dispose();
                    Frame_Login loginFrame = new Frame_Login();
                }
            }
        };
    }
}

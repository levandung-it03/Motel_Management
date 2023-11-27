package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages;

import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.Models.RegionModel;
import com.motel_management.Views.Frame_Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Listener_LogOut_Reset {
    public static ActionListener reSetAction(JFrame mainFrameApp, String currentRegion){
        return new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<RegionModel> res = RegionDAO.getInstance().selectByCondition("WHERE region LIKE \"%" + currentRegion + "%\"");
            RegionModel temp = res.get(0);
            RegionDAO.getInstance().delete(temp.getRegionId());

            for (RegionModel re:res){
                System.out.println(re.getRegionId());
                RegionDAO.getInstance().delete(re.getRegionId());
            }
            mainFrameApp.dispose();
            Frame_Login.startLoginFrame();
        }
        };

    }

    public static ActionListener logOutAction(JFrame mainFrameApp){
        return new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(new JPanel() , "Are you sure ?" ,
                        "Log Out Dialog" , JOptionPane.YES_NO_OPTION ,
                        JOptionPane.QUESTION_MESSAGE);
                if (choice == 0) {
                    mainFrameApp.dispose();
                    Frame_Login.startLoginFrame();
                }
            }
        };
    }
}

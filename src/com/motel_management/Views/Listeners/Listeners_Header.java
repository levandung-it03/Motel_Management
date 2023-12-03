package com.motel_management.Views.Listeners;

import com.motel_management.Controllers.Controller_Header;
import com.motel_management.Views.Graphics.Frame_Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listeners_Header {
    public static ActionListener resetRegionAction(JFrame mainFrameApp, String currentRegion) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller_Header.deleteRegion(currentRegion);
                mainFrameApp.dispose();
                new Frame_Login();
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
                    new Frame_Login();
                }
            }
        };
    }
}

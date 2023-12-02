package com.motel_management.Views.Listeners;

import com.motel_management.Controllers.Controller_ChooseRegion;
import com.motel_management.Controllers.Controller_Login;
import com.motel_management.Views.Graphics.Frame_ChangePassword;
import com.motel_management.Views.Graphics.Frame_ChooseRegion;
import com.motel_management.Views.Graphics.Frame_Login;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listeners_Login {
    public Listeners_Login() { super(); }

    public static ActionListener loginAction(Frame_Login mainLoginFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = mainLoginFrame.getUsernameField().getText();
                String p = mainLoginFrame.getPasswordField().getText();
                String user = Controller_Login.validate(u, p);
                if (user == null) {
                    JOptionPane.showMessageDialog(new JPanel(), "Information is not correct!", "Notice",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    mainLoginFrame.setVisible(false);
                    String currentRegion = Controller_ChooseRegion.checkIfRegionExisted();
                    if (currentRegion == null) {
                        Frame_ChooseRegion mainFrame = new Frame_ChooseRegion(user);
                    } else {
                        Frame_MainApplication mainApp = new Frame_MainApplication(user, currentRegion);
                    }
                }
            }
        };
    }

    public static ActionListener showChangePasswordFrameAction(Frame_Login mainLoginFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame_ChangePassword.startLoginFrame(mainLoginFrame);
                mainLoginFrame.setVisible(false);
            }
        };
    }
}

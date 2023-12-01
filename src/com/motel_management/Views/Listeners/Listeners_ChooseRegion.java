package com.motel_management.Views.Listeners;

import com.motel_management.Controllers.Controller_ChooseRegion;
import com.motel_management.Views.Graphics.Frame_ChooseRegion;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Listeners_ChooseRegion {
    public Listeners_ChooseRegion() { super(); }

    public static ActionListener chooseRegionAction(Frame_ChooseRegion mainChooseRegionFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(
                        new JPanel(),
                        "Are you sure you want to submit? This information can't be changed!",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    String newRegion = Objects.requireNonNull(mainChooseRegionFrame.getRegion().getSelectedItem()).toString();
                    Controller_ChooseRegion.setNewRegion(newRegion);

                    Frame_MainApplication mainApp = new Frame_MainApplication(mainChooseRegionFrame.getUser(), newRegion);

                    mainChooseRegionFrame.setVisible(false);
                }
            }
        };
    }
}

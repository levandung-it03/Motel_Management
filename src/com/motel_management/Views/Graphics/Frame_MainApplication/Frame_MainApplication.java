package com.motel_management.Views.Graphics.Frame_MainApplication;

import com.motel_management.Views.Configs;

import javax.swing.*;
import java.awt.*;

public class Frame_MainApplication extends JFrame {
    private final String user;
    private final String currentRegion;

    public Frame_MainApplication(String user, String currentRegion) {
        super("Motel Management");
        this.user = user;
        this.currentRegion = currentRegion;
        this.createMainFrame();
    }

    public void createMainFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setSize(Configs.fullWidth, Configs.fullHeight);
        setLayout(new BorderLayout());

        JPanel header = new Panel_Header(currentRegion, user, this);
        JPanel footer = new Panel_Footer();
        JPanel centralPanel = new CentralPanel(this);

        add(header, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
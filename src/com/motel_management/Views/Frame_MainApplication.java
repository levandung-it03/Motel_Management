package com.motel_management.Views;

import com.motel_management.Views.MainApplication.Graphics.*;

import javax.swing.*;
import java.awt.*;

public class Frame_MainApplication extends JFrame {
    String user;
    String currentRegion;

    private JPanel header;
    private JPanel centralPanel;
    private JPanel footer;

    public Frame_MainApplication(String user, String currentRegion) {
        super("Motel Management");
        this.user = user;
        this.currentRegion = currentRegion;
    }

    public static void startMainApplicationFrame(String user, String currentRegion) {
        Frame_MainApplication mainApp = new Frame_MainApplication(user, currentRegion);
        mainApp.createMainFrame(mainApp);
    }

    public void createMainFrame(Frame_MainApplication mainApp) {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setSize(Configs.fullWidth, Configs.fullHeight);
        setLayout(new BorderLayout());

        mainApp.header = new Header(currentRegion,user,this);
        mainApp.footer = new Footer();
        mainApp.centralPanel = new CentralPanel(this);

        add(mainApp.header, BorderLayout.NORTH);
        add(mainApp.centralPanel, BorderLayout.CENTER);
        add(mainApp.footer, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
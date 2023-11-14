package com.motel_management.Views;

import com.motel_management.Views.MainApplication.Graphics.*;

import javax.swing.*;
import java.awt.*;

public class Frame_MainApplication extends JFrame {
    String user;

    private JPanel header;
    private JPanel centralPanel;
    private JPanel footer;

    public Frame_MainApplication(String user) {
        super("Motel Management");
        this.user = user;
    }

    public static void startMainApplicationFrame(String user) {
        Frame_MainApplication mainApp = new Frame_MainApplication(user);
        mainApp.createMainFrame(mainApp);
    }

    public void createMainFrame(Frame_MainApplication mainApp) {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setSize(Configs.fullWidth, Configs.fullHeight);
        setLayout(new BorderLayout());

        mainApp.header = new Header(user);
        mainApp.footer = new Footer();
        mainApp.centralPanel = new CentralPanel();

        add(mainApp.header, BorderLayout.NORTH);
        add(mainApp.centralPanel, BorderLayout.CENTER);
        add(mainApp.footer, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

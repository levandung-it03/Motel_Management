package com.motel_management.Views;

import javax.swing.*;

public class Frame_MainApplication extends JFrame {
    String user;
    public Frame_MainApplication(String user) {
        super("Motel Management");
        this.user = user;
    }

    public static void startMainApplicationFrame(String user) {
        Frame_MainApplication mainApp = new Frame_MainApplication(user);
        mainApp.createMainFrame();
    }

    public void createMainFrame() {
        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

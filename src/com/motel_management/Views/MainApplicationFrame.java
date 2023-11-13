package com.motel_management.Views;

import javax.swing.*;

public class MainApplicationFrame extends JFrame {
    String user;
    public MainApplicationFrame(String user) {
        super("Motel Management");
        this.user = user;
    }

    public static void startMainApplicationFrame(String user) {
        MainApplicationFrame mainApp = new MainApplicationFrame(user);
        mainApp.createMainFrame();
    }

    public void createMainFrame() {
        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

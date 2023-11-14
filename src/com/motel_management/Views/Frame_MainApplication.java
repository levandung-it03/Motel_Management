package com.motel_management.Views;

import com.motel_management.Views.MainApplication.Panel.Category;
import com.motel_management.Views.MainApplication.Panel.CentralPanel;
import com.motel_management.Views.MainApplication.Panel.Footer;
import com.motel_management.Views.MainApplication.Panel.Header;

import javax.swing.*;
import java.awt.*;

public class Frame_MainApplication extends JFrame {
    int fullWidth = 1366, fullHeight = 768;
    String user;

    private JPanel header;
    private JPanel category;
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
        UIManager.put("Label.font", Configuration.labelFont);
        UIManager.put("Label.foreground", Configuration.blackTextColor);

        setSize(fullWidth, fullHeight);
        setLayout(new BorderLayout());

        mainApp.header = new Header(fullWidth, fullHeight, user);
        mainApp.category = new Category(fullWidth, fullHeight);
        mainApp.footer = new Footer(fullWidth, fullHeight);
        mainApp.centralPanel = new CentralPanel(fullWidth, fullHeight);

        add(mainApp.header, BorderLayout.NORTH);
        add(mainApp.category, BorderLayout.WEST);
        add(mainApp.centralPanel, BorderLayout.CENTER);
        add(mainApp.footer, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

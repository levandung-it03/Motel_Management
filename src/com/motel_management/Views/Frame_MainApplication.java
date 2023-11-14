package com.motel_management.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame_MainApplication extends JFrame {
    int fullWidth = 1366, fullHeight = 768;
    String user;
    private final JPanel header = new JPanel(new BorderLayout());
    private final JPanel category = new JPanel();
    private final JPanel centralPanel = new JPanel();
    private final JPanel footer = new JPanel();

    private final JPanel headerTools = new JPanel(new FlowLayout());

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

        mainApp.createHeader();
        mainApp.createCategory();
        mainApp.createCentralPanel();
        mainApp.createFooter();

        add(header, BorderLayout.NORTH);
        add(category, BorderLayout.WEST);
        add(centralPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createHeader() {
        JLabel headerLabel = new JLabel("Motel Management - Welcome " + user);
        header.setPreferredSize(new Dimension(fullWidth, fullHeight/22));
        header.setBackground(Configuration.lightGreen);

        headerLabel.setFont(headerLabel.getFont().deriveFont(24.0f));
        headerLabel.setBorder(new EmptyBorder(6, 50, 0, 0));
        headerLabel.setForeground(Configuration.blackTextColor);

        // headerTools: logout,...

        header.add(headerLabel, BorderLayout.WEST);
        header.add(headerTools, BorderLayout.EAST);
    }

    public void createCategory() {
        category.setBackground(Configuration.darkGreen);
        category.setPreferredSize(new Dimension(fullWidth/7, fullHeight*10/11));
    }

    public void createCentralPanel() {
        centralPanel.setPreferredSize(new Dimension(fullWidth*6/7, fullHeight*10/11));

    }


    public void createFooter() {
        footer.setPreferredSize(new Dimension(fullWidth, fullHeight/22));
        footer.setBackground(Configuration.lightGreen);

    }
}

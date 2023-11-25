package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends JPanel {
    private final String user;
    private final int headerWidth;
    private final int headerHeight;
    private final JPanel headerTools = new JPanel(new FlowLayout());

    // Constructor
    public Header(String user) {
        // Add Your Layout Here
        super(new BorderLayout());
        this.headerWidth = Configs.fullWidth;
        this.headerHeight = (int) (Configs.fullHeight / 22);
        this.user = user;
        this.createHeader();
    }

    public void createHeader() {
        setPreferredSize(new Dimension(headerWidth, headerHeight));
        setBackground(Configs.lightGreen);

        JLabel headerLabel = new JLabel("Motel Management - Welcome " + user);
        headerLabel.setFont(headerLabel.getFont().deriveFont(24.0f));
        headerLabel.setForeground(Configs.blackTextColor);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        // headerTools: logout,...
        headerTools.setBackground(Configs.lightGreen);

        add(headerLabel, BorderLayout.CENTER);
        add(headerTools, BorderLayout.EAST);
    }
}
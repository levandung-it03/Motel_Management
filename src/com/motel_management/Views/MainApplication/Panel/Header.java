package com.motel_management.Views.MainApplication.Panel;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends JPanel {
    private final String user;
    private final int headerWidth;
    private final int headerHeight;
    private final JPanel headerTools = new JPanel(new FlowLayout());
    
    public Header(int fullWidth, int fullHeight, String user) {
        super();
        this.headerWidth = fullWidth;
        this.headerHeight = (int) (fullHeight / 22);
        this.user = user;
        this.createHeader();
    }
    
    public void createHeader() {
        setPreferredSize(new Dimension(headerWidth, headerHeight));
        setBackground(Configuration.lightGreen);

        JLabel headerLabel = new JLabel("Motel Management - Welcome " + user);
        headerLabel.setFont(headerLabel.getFont().deriveFont(24.0f));
        headerLabel.setBorder(new EmptyBorder(6, headerWidth/20, 0, 0));
        headerLabel.setForeground(Configuration.blackTextColor);

        // headerTools: logout,...

        add(headerLabel, BorderLayout.NORTH);
        add(headerTools, BorderLayout.EAST);
    }
}

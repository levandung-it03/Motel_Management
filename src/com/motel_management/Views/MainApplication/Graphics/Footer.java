package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private final int footerWidth;
    private final int footerHeight;

    // Constructor
    public Footer() {
        // Add Your Layout Here
        super(new FlowLayout());
        this.footerWidth = Configuration.fullWidth;
        this.footerHeight = (int) (Configuration.fullHeight / 22);
        this.createFooter();
    }

    public void createFooter() {
        setPreferredSize(new Dimension(footerWidth, footerHeight));
        setBackground(Configuration.lightGreen);

        JLabel footerLabel = new JLabel("Powered By TDBT, contact: levandung.it03@gmail.com");
        footerLabel.setForeground(Configuration.greenTextColor);

        add(footerLabel);
    }
}

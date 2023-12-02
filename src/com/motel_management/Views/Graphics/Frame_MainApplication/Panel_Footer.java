package com.motel_management.Views.Graphics.Frame_MainApplication;
import com.motel_management.Views.Configs;

import javax.swing.*;
import java.awt.*;

public class Panel_Footer extends JPanel {
    private final int footerWidth;
    private final int footerHeight;

    // Constructor
    public Panel_Footer() {
        // Add Your Layout Here
        super(new FlowLayout());
        this.footerWidth = Configs.fullWidth;
        this.footerHeight = (int) (Configs.fullHeight / 22);
        this.createFooter();
    }

    public void createFooter() {
        setPreferredSize(new Dimension(footerWidth, footerHeight));
        setBackground(Configs.lightGreen);

        JLabel footerLabel = new JLabel("Powered By TDBT, contact: levandung.it03@gmail.com");
        footerLabel.setForeground(Configs.greenTextColor);

        add(footerLabel);
    }
}

package com.motel_management.Views.Graphics;

import com.motel_management.Controllers.Controller_ChooseRegion;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Listeners.Listeners_ChooseRegion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Frame_ChooseRegion extends JFrame {
    private final String user;
    private JComboBox<Object> region;
    private final JButton submitBtn = new JButton("Submit");

    // Constructor
    public Frame_ChooseRegion(String user) {
        super("Choose Your Region");
        this.user = user;
        this.createChooseRegionFrame();
        this.createOnsiteListeners();
    }

    public void createChooseRegionFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setSize(400, 270);
        setLayout(new BorderLayout());

        JPanel margin = new JPanel();
        margin.setBackground(Configs.mainWhiteBackground);
        margin.setBorder(new EmptyBorder(30, 0, 0, 0));

        JPanel mainContainerPanel = new JPanel(new BorderLayout(0, 15));
        mainContainerPanel.setBackground(Configs.mainWhiteBackground);
        mainContainerPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 140));

        JLabel label = new JLabel("Choose Region");
        label.setFont(Configs.labelFont);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 32.2f));

        region = new JComboBox<>(new String[] {"Tp.Ho Chi Minh", "Another Province"});
        region.setFont(Configs.labelFont);

        submitBtn.setPreferredSize(Configs.buttonDimension);
        submitBtn.setFont(Configs.labelFont);
        submitBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        submitBtn.setBackground(Configs.blackTextColor);
        submitBtn.setForeground(Configs.mainWhiteBackground);

        mainContainerPanel.add(label, BorderLayout.NORTH);
        mainContainerPanel.add(region, BorderLayout.CENTER);
        mainContainerPanel.add(submitBtn, BorderLayout.SOUTH);

        margin.add(mainContainerPanel);
        add(margin, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createOnsiteListeners() {
        submitBtn.addActionListener(Listeners_ChooseRegion.chooseRegionAction(this));
    }

    // Getters
    public JComboBox<Object> getRegion() { return region; }
    public String getUser() { return user; }
}

package com.motel_management.Views.Graphics;

import com.motel_management.Controllers.Controller_ChooseRegion;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

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
        Frame_ChooseRegion _this = this;
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(
                        new JPanel(),
                        "Are you sure you want to submit? This information can't be changed!",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    String newRegion = Objects.requireNonNull(_this.getRegion().getSelectedItem()).toString();
                    Controller_ChooseRegion.setNewRegion(newRegion);

                    Frame_MainApplication mainApp = new Frame_MainApplication(_this.getUser(), newRegion);

                    _this.setVisible(false);
                }
            }
        });
    }

    // Getters
    public JComboBox<Object> getRegion() { return region; }
    public String getUser() { return user; }
}

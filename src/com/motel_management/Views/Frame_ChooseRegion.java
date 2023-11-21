package com.motel_management.Views;

import com.motel_management.Controllers.Controller_ChooseRegion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Frame_ChooseRegion extends JFrame {
    JComboBox<Object> region;
    JButton submitBtn = new JButton("Submit");

    // Constructor
    public Frame_ChooseRegion() { super("Choose Your Region"); }

    public static void startChooseRegionFrame(String user) {
        Frame_ChooseRegion mainFrame = new Frame_ChooseRegion();
        mainFrame.createChooseRegionFrame();
        mainFrame.createOnsiteListeners(user, mainFrame);
    }

    public void createChooseRegionFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setSize(500, 300);
        setLayout(new BorderLayout());

        JPanel margin = new JPanel();
        margin.setBackground(Configs.normalGreen);
        margin.setBorder(new EmptyBorder(50, 0, 0, 0));

        JPanel mainContainerPanel = new JPanel(new BorderLayout(0, 15));
        mainContainerPanel.setBackground(Configs.normalGreen);
        mainContainerPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 140));

        JLabel label = new JLabel("Choose Region");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 30.0f));
        label.setHorizontalAlignment(JLabel.CENTER);

        region = new JComboBox<>(new String[] {"Tp.Ho Chi Minh", "Another Province"});
        region.setFont(Configs.labelFont);

        submitBtn.setPreferredSize(Configs.buttonDimension);
        submitBtn.setFont(Configs.labelFont);

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

    public void createOnsiteListeners(String user, Frame_ChooseRegion mainFrame) {
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(
                        new JPanel(),
                        "Are you sure you want to submit? This information can't be changed!",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    String newRegion = Objects.requireNonNull(region.getSelectedItem()).toString();
                    Controller_ChooseRegion.setNewRegion(newRegion);
                    Frame_MainApplication.startMainApplicationFrame(user, newRegion);
                    mainFrame.setVisible(false);
                }
            }
        });
    }
}

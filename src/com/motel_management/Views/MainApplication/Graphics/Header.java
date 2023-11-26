package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Frame_Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    private final String user;
    private final int headerWidth;
    private final int headerHeight;
    private final JPanel headerTools = new JPanel(new FlowLayout());
    JFrame mainFrameApp;

    // Constructor
    public Header(String user,JFrame mainFrameApp) {
        // Add Your Layout Here
        super(new BorderLayout());
        this.headerWidth = Configs.fullWidth;
        this.headerHeight = (int) (Configs.fullHeight / 22);
        this.user = user;
        this.mainFrameApp = mainFrameApp;
        this.createHeader();
        createLogOutButton();
        createReloadButton();
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
    public void createLogOutButton(){
        JPanel logOutPanel = new JPanel();
        logOutPanel.setOpaque(false);
        JButton logOutButton = new JButton("LOG OUT");

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(new JPanel(),"Are you sure ?",
                        "Show Confirm Dialog ", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (choice==0) {
                    mainFrameApp.dispose();
                    Frame_Login.startLoginFrame();
                }
            }
        });
        logOutPanel.add(logOutButton);
        add(logOutPanel,BorderLayout.EAST);
    }

    public void createReloadButton(){
        JPanel reLoadPanel = new JPanel();
        reLoadPanel.setOpaque(false);
        JButton reLoadButt = new JButton("RELOAD PAGE");
        reLoadButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.updateComponentTreeUI(mainFrameApp);
            }
        });

        reLoadPanel.add(reLoadButt);
        this.add(reLoadPanel,BorderLayout.WEST);
    }
}
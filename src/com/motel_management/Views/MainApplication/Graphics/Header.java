package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.DataAccessObject.RegionDAO;
import com.motel_management.Models.RegionModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Frame_ChooseRegion;
import com.motel_management.Views.Frame_Login;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_LogOut_Reset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Header extends JPanel {
    private final String user;
    String currentRegion;
    private final int headerWidth;
    private final int headerHeight;
    private final JPanel headerTools = new JPanel(new FlowLayout());
    JFrame mainFrameApp;

    // Constructor
    public Header(String currentRegion, String user,JFrame mainFrameApp) {
        // Add Your Layout Here
        super(new BorderLayout());
        this.headerWidth = Configs.fullWidth;
        this.headerHeight = (int) (Configs.fullHeight / 22);
        this.user = user;
        this.currentRegion = currentRegion;
        this.mainFrameApp = mainFrameApp;
        this.createHeader();
        createMenuOptions();
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
    public void createMenuOptions(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" OPTIONS ");
        JMenuItem reSetItem = new JMenuItem("RESET");
        JMenuItem logOutItem = new JMenuItem("LOG OUT");

        logOutItem.addActionListener(Listener_LogOut_Reset.logOutAction(mainFrameApp));
        reSetItem.addActionListener(Listener_LogOut_Reset.reSetAction(mainFrameApp,currentRegion));


        menu.add(reSetItem);
        menu.add(logOutItem);
        menuBar.add(menu);
        menuBar.setBorder(new EmptyBorder(5,5,5,5));
        add(menuBar,BorderLayout.EAST);
    }
}
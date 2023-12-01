package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Frame_MainApplication;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.Page_RoomsMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_CheckOut.Page_CheckOutMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water.Page_ElectricityWaterMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.Page_ContractMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.Page_InvoicesMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.Page_RepresentativesMain;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Statistic.Page_StatisticMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;


public class CentralPanel extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    public static JTabbedPane category;
    ArrayList<JPanel> pages = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();

    // Constructor
    public CentralPanel(Frame_MainApplication mainFrameApp) {
        // Add Your Layout Here
        super();
        this.mainFrameApp = mainFrameApp;
        this.createCentralPanel();
        this.createOnsiteListeners();
    }

    public void createCentralPanel() {
        category = new JTabbedPane(JTabbedPane.LEFT);
        category.setBounds(0, 0, Configs.centralPanelWidth, Configs.centralPanelHeight);

        labels.add(generateTabLabel("Statistic"));
        labels.add(generateTabLabel("Rooms"));
        labels.add(generateTabLabel("Contracts"));
        labels.add(generateTabLabel("Representatives"));
        labels.add(generateTabLabel("Electricity-Water"));
        labels.add(generateTabLabel("Invoices"));
        labels.add(generateTabLabel("Check-out"));

        pages.add(new Page_StatisticMain(mainFrameApp));
        pages.add(new Page_RoomsMain(mainFrameApp));
        pages.add(new Page_ContractMain(mainFrameApp));
        pages.add(new Page_RepresentativesMain(mainFrameApp));
        pages.add(new Page_ElectricityWaterMain());
        pages.add(new Page_InvoicesMain(mainFrameApp));
        pages.add(new Page_CheckOutMain());

        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setPreferredSize(
                    new Dimension(Configs.centralPanelWidth + 16, Configs.centralPanelHeight + 38)
            );

            category.addTab(null, pages.get(i));
            category.setTabComponentAt(i, labels.get(i));
            category.setBackgroundAt(i, Configs.lightGreen);
        }

        setBackground(Configs.darkGreen);
        add(category);
    }

    public JLabel generateTabLabel(String name) {
        JLabel l = new JLabel(name);
        l.setPreferredSize(new Dimension(220, 45));
        l.setForeground(Configs.blackTextColor);
        l.setBorder(new EmptyBorder(0, 10, 0, 0));
        l.setFont(l.getFont().deriveFont(18.0f));
        
        return l;
    }

    public void createOnsiteListeners() {
        category.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int tabSelected = category.getSelectedIndex();
                switch(tabSelected) {
                    case 0 -> category.setComponentAt(0, new Page_StatisticMain(mainFrameApp));
                    case 1 -> category.setComponentAt(1, new Page_RoomsMain(mainFrameApp));
                    case 2 -> category.setComponentAt(2, new Page_ContractMain(mainFrameApp));
                    case 3 -> category.setComponentAt(3, new Page_RepresentativesMain(mainFrameApp));
                    case 4 -> category.setComponentAt(4, new Page_ElectricityWaterMain());
                    case 5 -> category.setComponentAt(5, new Page_InvoicesMain(mainFrameApp));
                    case 6 -> category.setComponentAt(6, new Page_CheckOutMain());
                }
            }
        });
    }
}

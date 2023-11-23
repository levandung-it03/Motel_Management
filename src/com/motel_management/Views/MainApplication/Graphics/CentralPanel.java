package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.RoomPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_CheckOut.CheckOutPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water.Electricity_WaterPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesMainPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.RepresentativesPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.StatisticPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
    private final JFrame mainFrameApp;
    public static JTabbedPane category;
    ArrayList<JPanel> pages = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();

    // Constructor
    public CentralPanel(JFrame mainFrameApp) {
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

        pages.add(new StatisticPage());
        pages.add(new RoomPage());
        pages.add(new ContractPage());
        pages.add(new RepresentativesPage());
        pages.add(new Electricity_WaterPage());
        pages.add(new InvoicesMainPage(mainFrameApp));
        pages.add(new CheckOutPage());

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
                    case 0 -> category.setComponentAt(0, new StatisticPage());
                    case 1 -> category.setComponentAt(1, new RoomPage());
                    case 2 -> category.setComponentAt(2, new ContractPage());
                    case 3 -> category.setComponentAt(3, new RepresentativesPage());
                    case 4 -> category.setComponentAt(4, new Electricity_WaterPage());
                    case 5 -> category.setComponentAt(5, new InvoicesMainPage(mainFrameApp));
                    case 6 -> category.setComponentAt(6, new CheckOutPage());
                }
            }
        });
    }
}

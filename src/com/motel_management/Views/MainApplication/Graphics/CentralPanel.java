package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.*;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages.RoomPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.StatisticPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
    JTabbedPane category;
    ArrayList<JPanel> pages = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();

    // Constructor
    public CentralPanel() {
        // Add Your Layout Here
        super();
        this.createCentralPanel();
        this.createOnsiteListeners();
    }

    public void createCentralPanel() {
        setPreferredSize(new Dimension());
        category = new JTabbedPane(JTabbedPane.LEFT);
        category.setBounds(0, 0, Configs.centralPanelWidth, Configs.centralPanelHeight);

        labels.add(generateTabLabel("Statistic"));
        labels.add(generateTabLabel("Rooms"));
        labels.add(generateTabLabel("Contracts"));
        labels.add(generateTabLabel("Representatives"));
        labels.add(generateTabLabel("Electricity-Water"));
        labels.add(generateTabLabel("Consumption"));
        labels.add(generateTabLabel("Invoices"));

        pages.add(new StatisticPage());
        pages.add(new RoomPage());
        pages.add(new ContractsPage());
        pages.add(new RepresentativesPage());
        pages.add(new Electricity_WaterPage());
        pages.add(new ConsumptionPage());
        pages.add(new InvoicesPage());

        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

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
        l.setBorder(new EmptyBorder(0, 30, 0, 0));
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
                    case 2 -> category.setComponentAt(2, new ContractsPage());
                    case 3 -> category.setComponentAt(3, new RepresentativesPage());
                    case 4 -> category.setComponentAt(4, new Electricity_WaterPage());
                    case 5 -> category.setComponentAt(5, new ConsumptionPage());
                    case 6 -> category.setComponentAt(6, new InvoicesPage());
                }
            }
        });
    }
}

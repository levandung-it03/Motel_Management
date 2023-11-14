package com.motel_management.Views.MainApplication.Graphics;
import com.motel_management.Views.Configuration;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;

    JTabbedPane category;
    ArrayList<JPanel> pages = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();

    // Constructor
    public CentralPanel(int fullWidth, int fullHeight) {
        // Add Your Layout Here
        super();
        this.centralPanelWidth = (int) (fullWidth * 6) / 7;
        this.centralPanelHeight = (int) (fullHeight * 10) /11;
        this.createCentralPanel();
    }

    public void createCentralPanel() {
        setPreferredSize(new Dimension(centralPanelWidth, centralPanelHeight));
        category = new JTabbedPane(JTabbedPane.LEFT);
        category.setBounds(0, 0, centralPanelWidth, centralPanelHeight);

        labels.add(generateTabLabel("Statistic"));
        labels.add(generateTabLabel("Rooms"));
        labels.add(generateTabLabel("Contracts"));
        labels.add(generateTabLabel("Representatives"));
        labels.add(generateTabLabel("Electricity-Water"));
        labels.add(generateTabLabel("Consumption"));
        labels.add(generateTabLabel("Invoices"));

        pages.add(new StatisticPage(centralPanelWidth, centralPanelHeight));
        pages.add(new RoomsPage(centralPanelWidth, centralPanelHeight));
        pages.add(new ContractsPage(centralPanelWidth, centralPanelHeight));
        pages.add(new RepresentativesPage(centralPanelWidth, centralPanelHeight));
        pages.add(new Electricity_WaterPage(centralPanelWidth, centralPanelHeight));
        pages.add(new ConsumptionPage(centralPanelWidth, centralPanelHeight));
        pages.add(new InvoicesPage(centralPanelWidth, centralPanelHeight));

        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setPreferredSize(new Dimension(centralPanelWidth, centralPanelHeight));

            category.addTab(null, pages.get(i));
            category.setTabComponentAt(i, labels.get(i));
            category.setBackgroundAt(i, Configuration.lightGreen);
        }

        setBackground(Configuration.darkGreen);
        add(category);
    }

    public JLabel generateTabLabel(String name) {
        JLabel l = new JLabel(name);
        l.setPreferredSize(new Dimension(220, 45));
        l.setForeground(Configuration.blackTextColor);
        l.setBorder(new EmptyBorder(0, 30, 0, 0));
        l.setFont(l.getFont().deriveFont(18.0f));
        
        return l;
    }
}

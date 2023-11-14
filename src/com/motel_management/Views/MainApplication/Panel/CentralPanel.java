package com.motel_management.Views.MainApplication.Panel;
import com.motel_management.Views.Configuration;
import com.motel_management.Views.MainApplication.Panel.CentralPanels.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;

    JTabbedPane category;
    ArrayList<JPanel> categories = new ArrayList<JPanel>();
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

        labels.add(generateCategoryChild("Statistic"));
        labels.add(generateCategoryChild("Rooms"));
        labels.add(generateCategoryChild("Contracts"));
        labels.add(generateCategoryChild("Representatives"));
        labels.add(generateCategoryChild("Electricity-Water"));
        labels.add(generateCategoryChild("Consumption"));
        labels.add(generateCategoryChild("Invoices"));

        categories.add(new Statistic(centralPanelWidth, centralPanelHeight));
        categories.add(new Rooms(centralPanelWidth, centralPanelHeight));
        categories.add(new Contracts(centralPanelWidth, centralPanelHeight));
        categories.add(new Representatives(centralPanelWidth, centralPanelHeight));
        categories.add(new Electricity_Water(centralPanelWidth, centralPanelHeight));
        categories.add(new Consumption(centralPanelWidth, centralPanelHeight));
        categories.add(new Invoices(centralPanelWidth, centralPanelHeight));

        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).setPreferredSize(new Dimension(centralPanelWidth, centralPanelHeight));

            category.addTab(null, categories.get(i));
            category.setTabComponentAt(i, labels.get(i));
            category.setBackgroundAt(i, Configuration.lightGreen);
        }

        setBackground(Configuration.darkGreen);
        add(category);
    }

    public JLabel generateCategoryChild(String name) {
        JLabel l = new JLabel(name);
        l.setPreferredSize(new Dimension(220, 45));
        l.setForeground(Configuration.blackTextColor);
        l.setBorder(new EmptyBorder(0, 30, 0, 0));
        l.setFont(l.getFont().deriveFont(18.0f));

        return l;
    }
}

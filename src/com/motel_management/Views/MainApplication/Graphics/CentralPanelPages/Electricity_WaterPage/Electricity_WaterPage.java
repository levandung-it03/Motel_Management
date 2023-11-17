package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Electricity_WaterPage extends JPanel {
    private JTabbedPane mainPage;
    private JPanel EWListPanel;
    private JPanel addEWPanel;
    // Constructor
    public Electricity_WaterPage() {
        // Set Layout Here
        super();
        this.createElectricity_WaterPanel();
        this.createOnsiteListeners();
    }

    public void createElectricity_WaterPanel() {
        this.mainPage = new JTabbedPane(JTabbedPane.TOP);
        EWListPanel = new Electricity_WaterListPage();
//        addEWPanel = new JPanel();

        EWListPanel.setBackground(Configs.mainWhiteBackground);
//        addEWPanel.setBackground(Configs.mainWhiteBackground);

        mainPage.add("Electricity_Water List", EWListPanel);
        mainPage.add("Add New Electricity_Water", addEWPanel);

        add(mainPage);
    }

    public void createOnsiteListeners() {
        mainPage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainPage.getSelectedIndex() == 0) {
                    EWListPanel = new Electricity_WaterListPage();
                    mainPage.setComponentAt(0, EWListPanel);
                } else {
                    addEWPanel = new JPanel();
                    mainPage.setComponentAt(1, addEWPanel);
                }
            }
        });
    }
}

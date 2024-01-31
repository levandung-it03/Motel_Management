package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Electricity_Water;

import com.motel_management.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_ElectricityWaterMain extends JPanel {
    public static JTabbedPane mainEWPage;
    private JPanel EWListPanel;
    private JPanel addEWPanel;
    // Constructor
    public Page_ElectricityWaterMain() {
        // Set Layout Here
        super();
        this.createElectricity_WaterPanel();
        this.createOnsiteListeners();
    }

    public void createElectricity_WaterPanel() {
        this.mainEWPage = new JTabbedPane(JTabbedPane.TOP);
        EWListPanel = new Page_ElectricityWaterList();
        addEWPanel = new Page_AddElectricityWater();

        EWListPanel.setBackground(Configs.mainWhiteBackground);
        addEWPanel.setBackground(Configs.mainWhiteBackground);

        mainEWPage.add("Electricity_Water List", EWListPanel);
        mainEWPage.add("Add New Electricity_Water", addEWPanel);

        add(mainEWPage);
    }

    public void createOnsiteListeners() {
        mainEWPage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainEWPage.getSelectedIndex() == 0) {
                    EWListPanel = new Page_ElectricityWaterList();
                    mainEWPage.setComponentAt(0, EWListPanel);
                } else {
                    addEWPanel = new Page_AddElectricityWater();
                    mainEWPage.setComponentAt(1, addEWPanel);
                }
            }
        });
    }
}

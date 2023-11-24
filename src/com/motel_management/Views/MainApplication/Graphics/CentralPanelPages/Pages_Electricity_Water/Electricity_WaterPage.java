package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Electricity_WaterPage extends JPanel {
    public static JTabbedPane mainEWPage;
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
        this.mainEWPage = new JTabbedPane(JTabbedPane.TOP);
        EWListPanel = new Electricity_WaterListPage();
        addEWPanel = new AddElectricity_WaterPage();

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
                    EWListPanel = new Electricity_WaterListPage();
                    mainEWPage.setComponentAt(0, EWListPanel);
                } else {
                    addEWPanel = new AddElectricity_WaterPage();
                    mainEWPage.setComponentAt(1, addEWPanel);
                }
            }
        });
    }
}

package com.motel_management.Views.MainApplication.Panel.CentralPanelPages;

import javax.swing.*;

public class Electricity_Water extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Electricity_Water(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createElectricity_WaterPanel();
    }

    public void createElectricity_WaterPanel() {
        add(new JLabel("This is Electricity_Water!"));
    }
}

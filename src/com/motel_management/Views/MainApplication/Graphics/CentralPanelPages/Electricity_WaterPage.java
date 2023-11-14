package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class Electricity_WaterPage extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Electricity_WaterPage(int centralPanelWidth, int centralPanelHeight) {
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

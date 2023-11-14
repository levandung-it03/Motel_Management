package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class Electricity_WaterPage extends JPanel {
    // Constructor
    public Electricity_WaterPage() {
        // Set Layout Here
        super();
        this.createElectricity_WaterPanel();
    }

    public void createElectricity_WaterPanel() {
        add(new JLabel("This is Electricity_Water!"));
    }
}

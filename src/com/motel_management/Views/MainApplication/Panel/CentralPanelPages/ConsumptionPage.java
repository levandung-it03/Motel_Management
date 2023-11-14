package com.motel_management.Views.MainApplication.Panel.CentralPanelPages;

import javax.swing.*;

public class Consumption extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Consumption(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createConsumptionPanel();
    }

    public void createConsumptionPanel() {
        add(new JLabel("This is Consumption!"));
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class ConsumptionPage extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public ConsumptionPage(int centralPanelWidth, int centralPanelHeight) {
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

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class ConsumptionPage extends JPanel {
    // Constructor
    public ConsumptionPage() {
        // Set Layout Here
        super();
        this.createConsumptionPanel();
    }

    public void createConsumptionPanel() {
        add(new JLabel("This is Consumption!"));
    }
}

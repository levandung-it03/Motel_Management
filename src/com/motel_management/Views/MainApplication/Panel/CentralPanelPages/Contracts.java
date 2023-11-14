package com.motel_management.Views.MainApplication.Panel.CentralPanelPages;

import javax.swing.*;

public class Contracts extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Contracts(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createContractsPanel();
    }

    public void createContractsPanel() {
        add(new JLabel("This is Contracts!"));
    }
}

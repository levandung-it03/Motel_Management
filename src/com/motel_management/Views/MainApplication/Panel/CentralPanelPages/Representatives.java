package com.motel_management.Views.MainApplication.Panel.CentralPanelPages;

import javax.swing.*;

public class Representatives extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Representatives(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createRepresentativesPanel();
    }

    public void createRepresentativesPanel() {
        add(new JLabel("This is Representatives!"));
    }
}

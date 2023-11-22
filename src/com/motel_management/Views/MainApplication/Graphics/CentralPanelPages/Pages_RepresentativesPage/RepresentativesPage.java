package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class RepresentativesPage extends JPanel {
    // Constructor
    public RepresentativesPage() {
        // Set Layout Here
        super();
        this.createRepresentativesPanel();
    }

    public void createRepresentativesPanel() {
        add(new JLabel("This is Representatives!"));
    }
}

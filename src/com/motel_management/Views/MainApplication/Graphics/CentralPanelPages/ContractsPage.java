package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class ContractsPage extends JPanel {
    // Constructor
    public ContractsPage() {
        // Set Layout Here
        super();
        this.createContractsPanel();
    }

    public void createContractsPanel() {
        add(new JLabel("This is Contracts!"));
    }
}

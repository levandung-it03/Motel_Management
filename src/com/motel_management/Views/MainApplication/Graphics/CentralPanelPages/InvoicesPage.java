package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class InvoicesPage extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public InvoicesPage(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createInvoicesPanel();
    }

    public void createInvoicesPanel() {
        add(new JLabel("This is Invoices!"));
    }
}

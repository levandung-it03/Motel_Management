package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import javax.swing.*;

public class InvoicesPage extends JPanel {
    // Constructor
    public InvoicesPage() {
        // Set Layout Here
        super();
        this.createInvoicesPanel();
    }

    public void createInvoicesPanel() {
        add(new JLabel("This is Invoices!"));
    }
}

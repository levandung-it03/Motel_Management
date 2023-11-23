package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InvoicesMainPage extends JPanel {
    public static JTabbedPane mainPage;
    private JPanel invoicesListPanel;
    private JPanel addInvoicePanel;

    // Constructor
    public InvoicesMainPage() {
        // Set Layout Here
        super();
        this.createInvoicesPanel();
        this.createOnsiteListeners();
    }

    public void createInvoicesPanel() {
        mainPage = new JTabbedPane(JTabbedPane.TOP);
        invoicesListPanel = new InvoicesListPage();
        addInvoicePanel = new AddInvoicePage();

        invoicesListPanel.setBackground(Configs.mainWhiteBackground);
        addInvoicePanel.setBackground(Configs.mainWhiteBackground);

        mainPage.add("Invoices List", invoicesListPanel);
        mainPage.add("Add New Invoice", addInvoicePanel);

        add(mainPage);
    }

    public void createOnsiteListeners() {
        mainPage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainPage.getSelectedIndex() == 0) {
                    invoicesListPanel = new InvoicesListPage();
                    mainPage.setComponentAt(0, invoicesListPanel);
                } else {
                    addInvoicePanel = new AddInvoicePage();
                    mainPage.setComponentAt(1, addInvoicePanel);
                }
            }
        });
    }
}
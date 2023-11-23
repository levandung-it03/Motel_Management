package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InvoicesMainPage extends JPanel {
    public static JTabbedPane mainPage;
    private final JFrame mainFrameApp;
    private JPanel invoicesListPanel;
    private JPanel addInvoicePanel;

    // Constructor
    public InvoicesMainPage(JFrame mainFrameApp) {
        // Set Layout Here
        super();
        this.createInvoicesPanel();
        this.createOnsiteListeners();
        this.mainFrameApp = mainFrameApp;
    }

    public void createInvoicesPanel() {
        mainPage = new JTabbedPane(JTabbedPane.TOP);
        invoicesListPanel = new InvoicesListPage(mainFrameApp);
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
                    invoicesListPanel = new InvoicesListPage(mainFrameApp);
                    mainPage.setComponentAt(0, invoicesListPanel);
                } else {
                    addInvoicePanel = new AddInvoicePage();
                    mainPage.setComponentAt(1, addInvoicePanel);
                }
            }
        });
    }
}
package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Invoices;

import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_InvoicesMain extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    private JTabbedPane mainTabbedPane;
    private JPanel invoicesListPanel;
    private JPanel addInvoicePanel;

    public JTabbedPane getMainTabbedPane() { return this.mainTabbedPane; }

    // Constructor
    public Page_InvoicesMain(Frame_MainApplication mainFrameApp) {
        // Set Layout Here
        super();
        this.mainFrameApp = mainFrameApp;
        this.createInvoicesPanel();
        this.createOnsiteListeners();
    }

    public void createInvoicesPanel() {
        mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        invoicesListPanel = new Page_InvoicesList(mainFrameApp);
        addInvoicePanel = new Page_AddInvoice(this);

        invoicesListPanel.setBackground(Configs.mainWhiteBackground);
        addInvoicePanel.setBackground(Configs.mainWhiteBackground);

        mainTabbedPane.add("Invoices List", invoicesListPanel);
        mainTabbedPane.add("Add New Invoice", addInvoicePanel);

        add(mainTabbedPane);
    }

    public void createOnsiteListeners() {
        Page_InvoicesMain _this = this;
        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainTabbedPane.getSelectedIndex() == 0) {
                    invoicesListPanel = new Page_InvoicesList(mainFrameApp);
                    mainTabbedPane.setComponentAt(0, invoicesListPanel);
                } else {
                    addInvoicePanel = new Page_AddInvoice(_this);
                    mainTabbedPane.setComponentAt(1, addInvoicePanel);
                }
            }
        });
    }
}
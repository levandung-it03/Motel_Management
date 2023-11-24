package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class InvoicesOfRoomDialog extends JDialog {
    public ArrayList<InvoicePanelItem> invoicePanels = new ArrayList<>();
    JPanel mainPanel = new JPanel();
    JPanel toolPanel = new JPanel(new BorderLayout());
    JFrame mainFrameApp;
    InvoicesListPage invoicesList;
    private final String roomId;

    public InvoicesOfRoomDialog(JFrame mainFrameApp, String roomId, InvoicesListPage invoicesList) {
        super(mainFrameApp, "Invoices");
        this.mainFrameApp = mainFrameApp;
        this.invoicesList = invoicesList;
        this.roomId = roomId;
        this.createInvoicesOfRoom();
        this.createOnSiteListener();
    }

    public void createInvoicesOfRoom() {
        this.remove(mainPanel);
        int toolHeight = 50;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setSize(Configs.centralPanelWidth + 200, Configs.centralPanelHeight + toolHeight);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        ArrayList<InvoiceModel> invoices = Controller_Invoices.getInvoicesByRoomId(roomId);
        for (int i = 0; i < invoices.size(); i++)
            invoicePanels.add(new InvoicePanelItem(i, invoices.get(i), this));

        invoicePanels.forEach(panel -> mainPanel.add(panel));
        for (int i = 12 - invoicePanels.size(); i > 0; i--) {
            JPanel empty = new JPanel();
            empty.setPreferredSize(new Dimension(270, 100));
            mainPanel.add(empty);
        }
        toolPanel.setPreferredSize(new Dimension(Configs.centralPanelWidth + 200, toolHeight));

        this.add(toolPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void createOnSiteListener() {
        this.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e) {}
            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {
                invoicesList.removeAll();
                invoicesList.createInvoicesListPage();
                invoicesList.createListeners();
                invoicesList.revalidate();
                invoicesList.repaint();
                return;
            }
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
        });
    }
}

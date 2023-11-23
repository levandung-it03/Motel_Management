package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InvoicesOfRoomDialog extends JDialog {
    LinkedHashMap<String, InvoicePanelItem> invoicePanels = new LinkedHashMap<>();
    JPanel mainPanel = new JPanel();
    JPanel toolPanel = new JPanel(new BorderLayout());
    JFrame mainFrameApp;
    public InvoicesOfRoomDialog(JFrame mainFrameApp, String roomId) {
        super(mainFrameApp, "Invoices");
        this.mainFrameApp = mainFrameApp;

        this.createInvoicesOfRoom(roomId);
    }

    public void createInvoicesOfRoom(String roomId) {
        int toolHeight = 50;
        this.setModal(true);
        this.setSize(Configs.centralPanelWidth + 200, Configs.centralPanelHeight + toolHeight);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        ArrayList<InvoiceModel> invoices = Controller_Invoices.getInvoicesByRoomId(roomId);
        invoices.forEach(i -> {
            invoicePanels.put(i.getInvoiceId(), new InvoicePanelItem(i, this));
        });
        invoicePanels.forEach((key, panel) -> mainPanel.add(panel));
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
}

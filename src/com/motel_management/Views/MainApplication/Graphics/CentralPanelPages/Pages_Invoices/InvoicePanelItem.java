package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices.InvoicesOfRoomDialogListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class InvoicePanelItem extends JPanel {
    private final JButton updateStatusBtn = new JButton("Update Payment");
    private final JButton detailBtn = new JButton("Detail");
    private final InvoicesOfRoomDialog parentDialog;
    public final InvoiceModel invoice;


    public InvoicePanelItem(int ind, InvoiceModel invoice, InvoicesOfRoomDialog parentDialog) {
        super(new FlowLayout());
        this.invoice = invoice;
        this.createInvoicePanel();
        this.parentDialog = parentDialog;
        this.createListeners();
    }

    public void createInvoicePanel() {
        int panelHeight = 115;
        int panelWidth = 270;

        setPreferredSize(new Dimension(panelWidth, panelHeight + 70));
        setBorder(new LineBorder(Configs.greenTextColor, 1, true));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        JPanel monthLabel = new JPanel();
        JLabel title = new JLabel(invoice.getPaymentMonth() + "/" + invoice.getPaymentYear());
        title.setFont(Configs.labelFont);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        monthLabel.setPreferredSize(new Dimension(panelWidth, 30));
        monthLabel.setBorder(new LineBorder(Color.BLACK, 1, true));
        monthLabel.add(title);

        JPanel mainInvoicePanel = new JPanel(new BorderLayout());
        mainInvoicePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        JPanel leftColumn = new JPanel(new GridLayout(4, 0));
        JPanel rightColumn = new JPanel(new GridLayout(4, 0));

        leftColumn.setPreferredSize(new Dimension(panelWidth / 2, panelHeight - 40));
        rightColumn.setPreferredSize(new Dimension(panelWidth / 2, panelHeight - 40));
        LinkedHashMap<String, JLabel> labels = new LinkedHashMap<>();
        LinkedHashMap<String, JLabel> values = new LinkedHashMap<>();

        labels.put("invoiceIdLabel", new JLabel("Invoice Id:"));
        labels.put("dateCreatedLabel", new JLabel("Date Created:"));
        labels.put("totalLabel", new JLabel("Total:"));
        labels.put("wasPaidLabel", new JLabel("Was Paid:"));

        values.put("invoiceIdValue", new JLabel(invoice.getInvoiceId()));
        values.put("dateCreatedValue", new JLabel(sdf.format(invoice.getDateCreated())));
        values.put("totalValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getTotal())));
        values.put("wasPaidValue", new JLabel(invoice.getWasPaid().equals("1") ? "YES" : "NO"));

        title.setForeground(Configs.greenTextColor);
        labels.forEach((key, label) -> {
            label.setFont(Configs.labelFont);
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setForeground(Configs.greenTextColor);
            leftColumn.add(label);
        });

        values.forEach((key, value) -> {
            value.setFont(Configs.labelFont);
            value.setForeground(Configs.greenTextColor);
            rightColumn.add(value);
        });

        JPanel buttonsPanel = new JPanel(new BorderLayout(5, 0));

        updateStatusBtn.setFont(Configs.labelFont);
        updateStatusBtn.setBackground(new Color(75, 217, 72));

        detailBtn.setFont(Configs.labelFont);
        detailBtn.setBackground(new Color(126, 170, 255));
        detailBtn.setPreferredSize(new Dimension(90, 30));

        buttonsPanel.add(detailBtn, BorderLayout.WEST);
        buttonsPanel.add(updateStatusBtn, BorderLayout.CENTER);
        buttonsPanel.setPreferredSize(new Dimension(panelWidth, 30));

        JPanel centralPanel = new JPanel(new BorderLayout());

        Color mainColor = invoice.getWasPaid().equals("1")
                ? new Color(184, 207, 229)
                : new Color(229, 184, 184);

        monthLabel.setBackground(mainColor);
        leftColumn.setBackground(mainColor);
        rightColumn.setBackground(mainColor);
        buttonsPanel.setBackground(mainColor);
        centralPanel.setBackground(mainColor);
        this.setBackground(mainColor);

        leftColumn.setBorder(new EmptyBorder(3, 0, 4, 0));
        rightColumn.setBorder(new EmptyBorder(3, 0, 4, 0));
        centralPanel.add(leftColumn, BorderLayout.WEST);
        centralPanel.add(rightColumn, BorderLayout.EAST);
        mainInvoicePanel.add(centralPanel, BorderLayout.CENTER);
        mainInvoicePanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(monthLabel);
        add(mainInvoicePanel);
    }

    public void createListeners() {
        this.detailBtn.addActionListener(InvoicesOfRoomDialogListeners.viewDetailInvoice(invoice, parentDialog));
        this.updateStatusBtn.addActionListener(InvoicesOfRoomDialogListeners.updateInvoiceStatus(invoice, this));
    }
}

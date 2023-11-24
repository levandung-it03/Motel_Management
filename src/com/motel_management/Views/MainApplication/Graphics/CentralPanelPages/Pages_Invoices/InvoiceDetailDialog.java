package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class InvoiceDetailDialog extends JDialog {
    public InvoiceDetailDialog(InvoiceModel invoice, JDialog parentDialog) {
        super(parentDialog, "Invoice Detail");
        this.createInvoiceDetailDialog(invoice);
    }

    public void createInvoiceDetailDialog(InvoiceModel invoice) {
        int fullHeight = 400;
        int fullWith = 550;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.setModal(true);
        this.setSize(fullWith, fullHeight);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        JPanel leftColumn = new JPanel(new GridLayout(15, 1, 5, 5));
        JPanel rightColumn = new JPanel(new GridLayout(15, 1, 5, 5));
        leftColumn.setPreferredSize(new Dimension(fullWith/2, fullHeight));
        rightColumn.setPreferredSize(new Dimension(fullWith/2, fullHeight));
        leftColumn.setBorder(new EmptyBorder(20, 50, 20, 0));
        rightColumn.setBorder(new EmptyBorder(20, 0, 20, 50));

        LinkedHashMap<String, JLabel> labels = new LinkedHashMap<>();
        LinkedHashMap<String, JLabel> values = new LinkedHashMap<>();

        labels.put("invoiceIdLabel", new JLabel("Invoice Id:"));
        labels.put("roomIdLabel", new JLabel("Room Id:"));
        labels.put("defaultRoomPriceLabel", new JLabel("Room Price:"));
        labels.put("dateCreatedLabel", new JLabel("Date Created:"));
        labels.put("paymentTimeLabel", new JLabel("Payment Time:"));
        labels.put("formerElectricNumberLabel", new JLabel("Former Electric:"));
        labels.put("newElectricNumberLabel", new JLabel("New Electric:"));
        labels.put("formerWaterNumberLabel", new JLabel("Former Water:"));
        labels.put("newWaterNumberLabel", new JLabel("New Water:"));
        labels.put("garbageLabel", new JLabel("Garbage Fee:"));
        labels.put("wifiLabel", new JLabel("Wifi Fee:"));
        labels.put("vehicleLabel", new JLabel("Vehicle Fee:"));
        labels.put("totalLabel", new JLabel("Total:"));
        labels.put("wasPaidLabel", new JLabel("Was Paid:"));

        values.put("invoiceIdValue", new JLabel(invoice.getInvoiceId()));
        values.put("roomIdValue", new JLabel(invoice.getRoomId()));
        values.put("defaultRoomPriceValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getDefaultRoomPrice())));
        values.put("dateCreatedValue", new JLabel(sdf.format(invoice.getDateCreated())));
        values.put("paymentTimeValue", new JLabel(invoice.getPaymentMonth() + "/" + invoice.getPaymentYear()));
        values.put("formerElectricNumberValue", new JLabel(Integer.toString(invoice.getFormerElectricNumber())));
        values.put("newElectricNumberValue", new JLabel(Integer.toString(invoice.getNewElectricNumber())));
        values.put("formerWaterNumberValue", new JLabel(Integer.toString(invoice.getFormerWaterNumber())));
        values.put("newWaterNumberValue", new JLabel(Integer.toString(invoice.getNewWaterNumber())));
        values.put("garbageValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getGarbage())));
        values.put("wifiValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getWifi())));
        values.put("vehicleValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getVehicle())));
        values.put("totalValue", new JLabel(Configs.convertStringToVNDCurrency(invoice.getTotal())));
        values.put("wasPaidValue", new JLabel(invoice.getWasPaid().equals("1") ? "YES" : "NO"));

        labels.forEach((key, label) -> {
            label.setFont(Configs.labelFont);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 18.0f));
            label.setHorizontalAlignment(JLabel.LEFT);
            leftColumn.add(label);
        });
        values.forEach((key, value) -> {
            value.setFont(Configs.labelFont);
            value.setFont(value.getFont().deriveFont(18.0f));
            value.setHorizontalAlignment(JLabel.LEFT);
            rightColumn.add(value);
        });

        this.add(leftColumn, BorderLayout.WEST);
        this.add(rightColumn, BorderLayout.CENTER);
        this.setVisible(true);
    }
}

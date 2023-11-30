package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices.AddInvoiceListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class AddInvoicePage extends JPanel {
    JPanel container;
    JComboBox<Object> roomId;
    JTextField identifier = new JTextField(20);
    JTextField defaultRoomPrice = new JTextField(20);
    JTextField paymentYear = new JTextField(20);
    JTextField paymentMonth = new JTextField(20);

    JTextField formerElectricNumber = new JTextField(20);
    JTextField newElectricNumber = new JTextField(20);
    JTextField formerWaterNumber = new JTextField(20);
    JTextField newWaterNumber = new JTextField(20);

    JTextField garbage = new JTextField(20);
    JTextField wifi = new JTextField(20);
    JTextField vehicle = new JTextField(20);

    JButton submitBtn;
    JPanel submitBtnContainer = new JPanel();
    AddInvoiceListeners addInvoiceObj;

    // Constructor
    public AddInvoicePage() {
        super(new FlowLayout());
        this.createAddInvoicePage();
        this.createListeners();
    }

    public void createAddInvoicePage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 40, 0, 40));

        this.addInvoiceObj = new AddInvoiceListeners();
        this.roomId = this.addInvoiceObj.createRoomIdComboBox();
        container.add(InputComboPanel.generateComboBoxInputPanel("Room Code", roomId));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price", defaultRoomPrice));
        container.add(InputComboPanel.generateTextInputPanel("Payment Year", paymentYear));
        container.add(InputComboPanel.generateTextInputPanel("Payment Month", paymentMonth));
        container.add(InputComboPanel.generateTextInputPanel("Black Digits - Old ELECTRIC", formerElectricNumber));
        container.add(InputComboPanel.generateTextInputPanel("Black Digits - New ELECTRIC", newElectricNumber));
        container.add(InputComboPanel.generateTextInputPanel("Black Digits - Old WATER", formerWaterNumber));
        container.add(InputComboPanel.generateTextInputPanel("Black Digits - New WATER", newWaterNumber));
        container.add(InputComboPanel.generateTextInputPanel("Garbage Fee", garbage));
        container.add(InputComboPanel.generateTextInputPanel("Wifi Fee", wifi));
        container.add(InputComboPanel.generateTextInputPanel("Vehicle Fee", vehicle));

        this.submitBtn = InputComboPanel.generateButton("Submit");
        this.submitBtnContainer.add(this.submitBtn);
        this.submitBtnContainer.setBorder(new EmptyBorder(10, 55, 0, 55));
        container.add(this.submitBtnContainer);

        add(container);
    }

    public void createListeners() {
        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("defaultRoomPrice", this.defaultRoomPrice);
        inpTags.put("paymentYear", this.paymentYear);
        inpTags.put("paymentMonth", this.paymentMonth);
        inpTags.put("formerElectricNumber", this.formerElectricNumber);
        inpTags.put("newElectricNumber", this.newElectricNumber);
        inpTags.put("formerWaterNumber", this.formerWaterNumber);
        inpTags.put("newWaterNumber", this.newWaterNumber);
        inpTags.put("garbage", this.garbage);
        inpTags.put("wifi", this.wifi);
        inpTags.put("vehicle", this.vehicle);

        AddInvoiceListeners.automaticallySetValueTextField(roomId, inpTags);
        this.roomId.addActionListener(AddInvoiceListeners.changeInpValuesWhenRoomIdChanged(this.roomId, inpTags));
        this.submitBtn.addActionListener(AddInvoiceListeners.addNewInvoicesListener(roomId, inpTags));
    }
}
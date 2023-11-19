package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices.AddInvoiceListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AddInvoicePage extends JPanel {
    JPanel container;
    JComboBox<Object> roomId;
    JTextField identifier = new JTextField(20);
    JTextField defaultRoomPrice = new JTextField(20);
    JTextField yearPayment = new JTextField(20);
    JTextField monthPayment = new JTextField(20);

    JTextField formerElectricNumber = new JTextField(20);
    JTextField currentElectricNumber = new JTextField(20);
    JTextField formerWaterNumber = new JTextField(20);
    JTextField currentWaterNumber = new JTextField(20);

    JTextField garbage = new JTextField(20);
    JTextField wifi = new JTextField(20);
    JTextField vehicle = new JTextField(20);
    JTextField total = new JTextField(20);

    JButton submitBtn;
    AddInvoiceListeners addInvoiceObj;

    // Constructor
    public AddInvoicePage() {
        super(new FlowLayout());
        this.createAddRoomPage();
        this.createListeners();
    }

    public void createAddRoomPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 40, 0, 40));


        this.addInvoiceObj = new AddInvoiceListeners();
        this.roomId = this.addInvoiceObj.createRoomIdComboBox();
        container.add(InputComboPanel.generateComboBoxInputPanel("Room Code (*)", roomId));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price", defaultRoomPrice));
        container.add(InputComboPanel.generateTextInputPanel("Payment Year (*)", yearPayment));
        container.add(InputComboPanel.generateTextInputPanel("Payment Month (*)", monthPayment));
        container.add(InputComboPanel.generateTextInputPanel("Former Electricity (*)", formerElectricNumber));
        container.add(InputComboPanel.generateTextInputPanel("Current Electricity (*)", currentElectricNumber));
        container.add(InputComboPanel.generateTextInputPanel("Former Water Number (*)", formerWaterNumber));
        container.add(InputComboPanel.generateTextInputPanel("Current Water Number (*)", currentWaterNumber));
        container.add(InputComboPanel.generateTextInputPanel("Garbage Fee(*)", garbage));
        container.add(InputComboPanel.generateTextInputPanel("Wifi Fee (*)", wifi));
        container.add(InputComboPanel.generateTextInputPanel("Vehicle Fee (*)", vehicle));
        container.add(InputComboPanel.generateTextInputPanel("Total Payment", total));

        this.submitBtn = InputComboPanel.generateButton("Submit");
        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
//        this.identifier.setText("038203032578");
//        this.lastName.setText("Le Van");
//        this.firstname.setText("Dung");
//        this.phone.setText("0377863928");
//        this.jobTitle.setText("Sinh Vien");
//        this.permanentAddress.setText("Bien Hoa");
//        this.email.setText("levandung.it03@gmail.com");
//        this.bankAccountNumber.setText("21097877");
//        this.quantity.setText("5");
//        this.roomDeposit.setText("3000000");

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("defaultRoomPrice", this.defaultRoomPrice);
        inpTags.put("yearPayment", this.yearPayment);
        inpTags.put("monthPayment", this.monthPayment);
        inpTags.put("formerElectricNumber", this.formerElectricNumber);
        inpTags.put("currentElectricNumber", this.currentElectricNumber);
        inpTags.put("formerWaterNumber", this.formerWaterNumber);
        inpTags.put("currentWaterNumber", this.currentWaterNumber);
        inpTags.put("garbage", this.garbage);
        inpTags.put("wifi", this.wifi);
        inpTags.put("vehicle", this.vehicle);
        inpTags.put("total", this.total);

        this.roomId.addActionListener(AddInvoiceListeners.changeInpValuesWhenRoomIdChanged(this.roomId, inpTags));
        this.submitBtn.addActionListener(AddInvoiceListeners.addNewInvoicesListener(roomId, inpTags));
    }
}

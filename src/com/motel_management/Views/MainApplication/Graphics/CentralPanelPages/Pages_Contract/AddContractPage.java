package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.AddContractListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AddContractPage extends JPanel {
    JPanel container;
    JTextField identifier = new JTextField(20);
    JTextField lastName = new JTextField(20);
    JTextField firstname = new JTextField(20);
    JTextField birthday = new JTextField(20);
    JTextField gender = new JTextField(20);
    JTextField phone = new JTextField(20);
    JTextField jobTitle = new JTextField(20);
    JTextField permanentAddress = new JTextField(20);
    JTextField email = new JTextField(20);
    JTextField creditCard = new JTextField(20);
    JTextField bank = new JTextField(20);
    JTextField roomId = new JTextField(20);
    JTextField quantity = new JTextField(20);
    JTextField roomDeposit = new JTextField(20);
    JTextField startingDate = new JTextField(20);
    JTextField endingDate = new JTextField(20);

    JButton submitBtn;

    // Constructor
    public AddContractPage() {
        super(new FlowLayout());
        this.createAddRoomPage();
        this.createListeners();
    }

    public void createAddRoomPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*9/20));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(InputComboPanel.generateTextInputPanel("CCCD (CMND)", identifier));
        container.add(InputComboPanel.generateTextInputPanel("Last Name", lastName));
        container.add(InputComboPanel.generateTextInputPanel("First Name", firstname));
        container.add(InputComboPanel.generateTextInputPanel("Birth Day", birthday));
        container.add(InputComboPanel.generateTextInputPanel("Gender", gender));
        container.add(InputComboPanel.generateTextInputPanel("Phone", phone));
        container.add(InputComboPanel.generateTextInputPanel("Job", jobTitle));
        container.add(InputComboPanel.generateTextInputPanel("Permanent Address", permanentAddress));
        container.add(InputComboPanel.generateTextInputPanel("Email", email));
        container.add(InputComboPanel.generateTextInputPanel("Credit Card", creditCard));
        container.add(InputComboPanel.generateTextInputPanel("Bank Name", bank));
        container.add(InputComboPanel.generateTextInputPanel("Room Code", roomId));
        container.add(InputComboPanel.generateTextInputPanel("Number Of People", quantity));
        container.add(InputComboPanel.generateTextInputPanel("Deposit (VNĐ)", roomDeposit));
        container.add(InputComboPanel.generateTextInputPanel("Started Date", startingDate));
        container.add(InputComboPanel.generateTextInputPanel("Ended Date", endingDate));

        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("identifier", this.identifier);
        inpTags.put("lastName", this.lastName);
        inpTags.put("firstname", this.firstname);
        inpTags.put("birthday", this.birthday);
        inpTags.put("gender", this.gender);
        inpTags.put("phone", this.phone);
        inpTags.put("jobTitle", this.jobTitle);
        inpTags.put("permanentAddress", this.permanentAddress);
        inpTags.put("email", this.email);
        inpTags.put("creditCard", this.creditCard);
        inpTags.put("bank", this.bank);
        inpTags.put("roomId", this.roomId);
        inpTags.put("quantity", this.quantity);
        inpTags.put("roomDeposit", this.roomDeposit);
        inpTags.put("startingDate", this.startingDate);
        inpTags.put("endingDate", this.endingDate);

        this.submitBtn.addActionListener(AddContractListeners.addNewContractListener(inpTags));
    }
}

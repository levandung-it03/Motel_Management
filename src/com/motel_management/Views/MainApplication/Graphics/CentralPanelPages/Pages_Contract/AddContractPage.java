package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.AddContractListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class AddContractPage extends JPanel {
    JPanel container;
    JTextField identifier = new JTextField(20);
    JTextField lastName = new JTextField(20);
    JTextField firstname = new JTextField(20);
    JDateChooser birthday = new JDateChooser();
    JComboBox<Object> gender;
    JTextField phone = new JTextField(20);
    JTextField jobTitle = new JTextField(20);
    JTextField permanentAddress = new JTextField(20);
    JTextField email = new JTextField(20);
    JTextField creditCard = new JTextField(20);
    JComboBox<Object> bank = new JComboBox<>();
    JComboBox<Object> roomId;
    JTextField quantity = new JTextField(20);
    JTextField roomDeposit = new JTextField(20);
    JDateChooser startingDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    JDateChooser endingDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    JButton submitBtn;

    HashMap<String, Integer> maxQuantity = new HashMap<>();

    // Constructor
    public AddContractPage() {
        super(new FlowLayout());
        this.createAddRoomPage();
        this.createListeners();
    }

    public void createAddRoomPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 40, 0, 40));

        String[] genders = {"Men", "Women"};
        this.gender = new JComboBox<Object>(genders);

        String[] banks = {"", "ABB", "ACB", "AGRIBANK", "BACABANK", "BID", "CTG", "EIB", "HDBANK", "KLB", "LIENVIET", "MBB",
                "MSB", "NAMA", "NCB", "OCB", "PGBANK", "PVCOMBANK", "SCB", "SEABANK", "SGB", "SHB", "STB", "TCB", "TPB",
                "VCB", "VIB", "VIETABANK", "VIETCAPITALBANK", "VPB", "VIETBANK"};

        this.bank = new JComboBox<>(banks);
        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("WHERE (quantity > 0)");
        for (RoomModel r: roomList)
            this.maxQuantity.put(r.getRoomId(), r.getMaxQuantity());

        this.roomId = new JComboBox<>(roomList.stream().map(RoomModel::getRoomId).toArray());

        container.add(InputComboPanel.generateTextInputPanel("Identity Card (*)", identifier));
        container.add(InputComboPanel.generateTextInputPanel("Last Name (*)", lastName));
        container.add(InputComboPanel.generateTextInputPanel("First Name (*)", firstname));
        container.add(InputComboPanel.generateDateInputPanel("Birthday (*)", birthday));
        container.add(InputComboPanel.generateComboBoxInputPanel("Gender (*)", gender));
        container.add(InputComboPanel.generateTextInputPanel("Phone (*)", phone));
        container.add(InputComboPanel.generateTextInputPanel("Job (*)", jobTitle));
        container.add(InputComboPanel.generateTextInputPanel("Permanent Address (*)", permanentAddress));
        container.add(InputComboPanel.generateTextInputPanel("Email", email));
        container.add(InputComboPanel.generateTextInputPanel("Credit Card", creditCard));
        container.add(InputComboPanel.generateComboBoxInputPanel("Bank Name", bank));
        container.add(InputComboPanel.generateComboBoxInputPanel("Room Code (*)", roomId));
        container.add(InputComboPanel.generateTextInputPanel("Number Of People (*)", quantity));
        container.add(InputComboPanel.generateTextInputPanel("Deposit (VNĐ) (*)", roomDeposit));
        container.add(InputComboPanel.generateDateInputPanel("Started Date (*)", startingDate));
        container.add(InputComboPanel.generateDateInputPanel("Ended Date (*)", endingDate));

        this.submitBtn = InputComboPanel.generateButton("Submit");
        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
        HashMap<String, JTextField> inpTags = new HashMap<>();
        HashMap<String, JDateChooser> dateTags = new HashMap<>();
        HashMap<String, JComboBox<Object>> comboTags = new HashMap<>();
        inpTags.put("identifier", this.identifier);
        inpTags.put("lastName", this.lastName);
        inpTags.put("firstname", this.firstname);
        dateTags.put("birthday", this.birthday);
        inpTags.put("phone", this.phone);
        inpTags.put("jobTitle", this.jobTitle);
        inpTags.put("permanentAddress", this.permanentAddress);
        inpTags.put("email", this.email);
        inpTags.put("creditCard", this.creditCard);
        comboTags.put("bank", this.bank);
        inpTags.put("quantity", this.quantity);
        inpTags.put("roomDeposit", this.roomDeposit);
        dateTags.put("startingDate", this.startingDate);
        dateTags.put("endingDate", this.endingDate);
        comboTags.put("gender", gender);
        comboTags.put("roomId", roomId);

        this.submitBtn.addActionListener(AddContractListeners.addNewContractListener(inpTags, dateTags, comboTags, maxQuantity));
    }
}

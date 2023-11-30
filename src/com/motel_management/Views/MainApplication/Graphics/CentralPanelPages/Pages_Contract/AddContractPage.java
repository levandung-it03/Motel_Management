package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.AddContractListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
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
    JTextField bankAccountNumber = new JTextField(20);
    JComboBox<Object> bank = new JComboBox<>();
    JComboBox<Object> roomId;
    JTextField quantity = new JTextField(20);
    JTextField roomDeposit = new JTextField(20);
    JDateChooser startingDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    JDateChooser endingDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    JComboBox<Object> isFamily = new JComboBox<Object>(new String[] {"NO", "YES"});
    JComboBox<Object> isRegisteredPerAddress = new JComboBox<Object>(new String[] {"NO", "YES"});
    JButton submitBtn;

    // Constructor
    public AddContractPage() {
        super(new FlowLayout());
        this.createAddContractPage();
        this.createListeners();
    }

    public void createAddContractPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 40, 0, 40));

        String[] genders = {"Men", "Women"};
        this.gender = new JComboBox<Object>(genders);

        String[] banks = {"", "ABB", "ACB", "AGRIBANK", "BACABANK", "BID", "CTG", "EIB", "HDBANK", "KLB", "LIENVIET", "MBB",
                "MSB", "NAMA", "NCB", "OCB", "PGBANK", "PVCOMBANK", "SCB", "SEABANK", "SGB", "SHB", "STB", "TCB", "TPB",
                "VCB", "VIB", "VIETABANK", "VIETCAPITALBANK", "VPB", "VIETBANK"};
        this.bank = new JComboBox<>(banks);

        this.roomId = AddContractListeners.createRoomIdComboBox();
        container.add(InputComboPanel.generateTextInputPanel("Identity Card (*)", identifier));
        container.add(InputComboPanel.generateTextInputPanel("Last Name (*)", lastName));
        container.add(InputComboPanel.generateTextInputPanel("First Name (*)", firstname));
        container.add(InputComboPanel.generateDateInputPanel("Birthday (*)", birthday));
        container.add(InputComboPanel.generateComboBoxInputPanel("Gender (*)", gender));
        container.add(InputComboPanel.generateTextInputPanel("Phone (*)", phone));
        container.add(InputComboPanel.generateTextInputPanel("Job (*)", jobTitle));
        container.add(InputComboPanel.generateTextInputPanel("Permanent Address (*)", permanentAddress));
        container.add(InputComboPanel.generateTextInputPanel("Email", email));
        container.add(InputComboPanel.generateTextInputPanel("Bank Account Number", bankAccountNumber));
        container.add(InputComboPanel.generateComboBoxInputPanel("Bank Name", bank));
        container.add(InputComboPanel.generateComboBoxInputPanel("Room Code (*)", roomId));
        container.add(InputComboPanel.generateTextInputPanel("People (Unknown = -1) (*)", quantity));
        container.add(InputComboPanel.generateTextInputPanel("Deposit (VNĐ) (*)", roomDeposit));
        container.add(InputComboPanel.generateDateInputPanel("Started Date (*)", startingDate));
        container.add(InputComboPanel.generateDateInputPanel("Ended Date (*)", endingDate));
        container.add(InputComboPanel.generateComboBoxInputPanel("Is A Family?", isFamily));
        container.add(InputComboPanel.generateComboBoxInputPanel("Register Temp Household?", isRegisteredPerAddress));

        this.submitBtn = InputComboPanel.generateButton("Submit");
        JPanel submitBtnContainer = new JPanel();
        submitBtnContainer.add(this.submitBtn);
        submitBtnContainer.setBorder(new EmptyBorder(10, 100, 0, 55));
        container.add(submitBtnContainer);

        add(container);
    }

    public void createListeners() {
        this.identifier.setText("038203032578");
        this.lastName.setText("Le Van");
        this.firstname.setText("Dung");
        this.phone.setText("0377863928");
        this.jobTitle.setText("Sinh Vien");
        this.permanentAddress.setText("Bien Hoa");
        this.email.setText("levandung.it03@gmail.com");
        this.bankAccountNumber.setText("21097877");
        this.quantity.setText("5");
        this.roomDeposit.setText("3000000");

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
        inpTags.put("bankAccountNumber", this.bankAccountNumber);
        comboTags.put("bank", this.bank);
        inpTags.put("quantity", this.quantity);
        inpTags.put("roomDeposit", this.roomDeposit);
        dateTags.put("startingDate", this.startingDate);
        dateTags.put("endingDate", this.endingDate);
        comboTags.put("gender", gender);
        comboTags.put("roomId", roomId);
        comboTags.put("isFamily", isFamily);
        comboTags.put("isRegisteredPerAddress", isRegisteredPerAddress);

        this.submitBtn.addActionListener(AddContractListeners.addNewContractListener(inpTags, dateTags, comboTags));
    }
}

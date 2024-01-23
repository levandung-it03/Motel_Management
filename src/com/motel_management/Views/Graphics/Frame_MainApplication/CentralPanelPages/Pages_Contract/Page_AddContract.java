package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Contract.AddContractListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

public class Page_AddContract extends JPanel {
    private final Page_ContractMain mainTabbedPane;

    private final JTextField identifier = new JTextField(20);
    private final JTextField lastName = new JTextField(20);
    private final JTextField firstname = new JTextField(20);
    private final JTextField phone = new JTextField(20);
    private final JTextField jobTitle = new JTextField(20);
    private final JTextField permanentAddress = new JTextField(20);
    private final JTextField email = new JTextField(20);
    private final JTextField bankAccountNumber = new JTextField(20);
    private final JTextField quantity = new JTextField(20);
    private final JTextField roomDeposit = new JTextField(20);

    private final JDateChooser birthday = new JDateChooser();
    private final JDateChooser endingDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    private final JDateChooser startingDate = new JDateChooser(Date.valueOf(LocalDate.now()));

    private final JComboBox<Object> bank = new JComboBox<>(
            new String[]{"", "ABB", "ACB", "AGRIBANK", "BACABANK", "BID", "CTG", "EIB", "HDBANK", "KLB", "LIENVIET", "MBB",
                    "MSB", "NAMA", "NCB", "OCB", "PGBANK", "PVCOMBANK", "SCB", "SEABANK", "SGB", "SHB", "STB", "TCB", "TPB",
                    "VCB", "VIB", "VIETABANK", "VIETCAPITALBANK", "VPB", "VIETBANK"}
    );
    private final JComboBox<Object> gender = new JComboBox<Object>(new String[]{"Men", "Women"});
    private final JComboBox<Object> isFamily = new JComboBox<Object>(new String[]{"NO", "YES"});
    private final JComboBox<Object> isRegisteredPerAddress = new JComboBox<Object>(new String[]{"NO", "YES"});
    private final JComboBox<Object> roomId = AddContractListeners.createRoomIdComboBox();
    ;

    private final JButton submitBtn = InputComboPanel.generateButton("Submit");
    private HashMap<String, JTextField> inpTags;

    // Constructor
    public Page_AddContract(Page_ContractMain mainTabbedPane) {
        super(new FlowLayout());
        this.mainTabbedPane = mainTabbedPane;
        this.createAddContractPage();
        this.createListeners();
    }

    public void createAddContractPage() {
        JPanel container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 40, 0, 40));

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

        JPanel submitBtnContainer = new JPanel();
        submitBtnContainer.add(this.submitBtn);
        submitBtnContainer.setBorder(new EmptyBorder(10, 100, 0, 55));
        container.add(submitBtnContainer);

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

        inpTags.get("identifier").setText("123123123132");
        inpTags.get("lastName").setText("Le Van");
        inpTags.get("firstname").setText("Dung");
        inpTags.get("phone").setText("0377863928");
        inpTags.get("jobTitle").setText("It");
        inpTags.get("permanentAddress").setText("Bien Hoa, Dong Nai");
        inpTags.get("email").setText("lvd@gmail.com");
        inpTags.get("quantity").setText("5");
        inpTags.get("roomDeposit").setText("300000");

        this.submitBtn.addActionListener(AddContractListeners.addNewContractListener(inpTags, dateTags, comboTags, this.mainTabbedPane));
    }
}

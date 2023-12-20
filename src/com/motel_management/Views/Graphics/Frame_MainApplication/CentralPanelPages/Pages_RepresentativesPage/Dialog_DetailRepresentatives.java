package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Representatives.RepresentativesListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class Dialog_DetailRepresentatives extends JDialog{
    private final JPanel subPanel0 = new JPanel();
    private final JPanel subPanel1 = new JPanel();
    private final JPanel subPanel2 = new JPanel();
    private JTextField identifier;
    private JTextField roomId;
    private JTextField fullName;
    private JTextField birthday;
    private JTextField gender;
    private JTextField phone;
    private JTextField jobTitle;
    private JTextField email;
    private JTextField bankAccountNumber;
    private JTextField bankTextField = new JTextField();
    private JTextArea permanentAddress;
    private JButton button = new JButton("UPDATE");
    private final JComboBox<String> bank = new JComboBox<>();
    String[] values ={"", "ABB", "ACB", "AGRIBANK", "BACABANK", "BID", "CTG", "EIB", "HDBANK", "KLB", "LIENVIET", "MBB",
                    "MSB", "NAMA", "NCB", "OCB", "PGBANK", "PVCOMBANK", "SCB", "SEABANK", "SGB", "SHB", "STB", "TCB", "TPB",
                    "VCB", "VIB", "VIETABANK", "VIETCAPITALBANK", "VPB", "VIETBANK"};


    public Dialog_DetailRepresentatives(JFrame mainAppFrame, PersonModel person){
        super(mainAppFrame);
        this.setTitle("Details Information");
        showInformationById(person);
    }

    public void addBankListCombobox(){
        for (String value : values) {
            bank.addItem(value);
        }
    }
    public void showInformationById(PersonModel person){
        //Generate
        JPanel marginPanel = new JPanel(new GridLayout(1,3));

        JPanel buttonPanel = new JPanel();
        JPanel imagePanel = new JPanel(new FlowLayout());


        ImageIcon icon = new ImageIcon("src/com/motel_management/Assets/img/Representative.png");
        JLabel labelForIcon = new JLabel(icon);

        addBankListCombobox();


        identifier = new JTextField(person.getIdentifier());
        roomId = new JTextField(person.getRoomId());
        fullName = new JTextField(person.getLastName()+" "+person.getFirstName());
        birthday = new JTextField("" + person.getBirthday());
        gender = new JTextField(person.getGender());
        phone = new JTextField(person.getPhone());
        jobTitle = new JTextField(person.getJobTitle());
        email = new JTextField(person.getEmail());
        bankAccountNumber = new JTextField(person.getBankAccountNumber());
        permanentAddress = new JTextArea(person.getPermanentAddress());
        bank.setSelectedItem(person.getBank());
        bankTextField.setText(person.getBank());


        //Set component
        button.setPreferredSize(new Dimension(14,14));
        button.setFont(button.getFont().deriveFont(16.0f));

        identifier.setHorizontalAlignment(SwingConstants.CENTER);

        labelForIcon.setBorder(new EmptyBorder(5,0,15,30));
        labelForIcon.setHorizontalAlignment(SwingConstants.CENTER);

        button.setPreferredSize(new Dimension(120,50));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 5, 0));


        imagePanel.setBorder(new EmptyBorder(80,0,0,0));
        imagePanel.setPreferredSize(new Dimension(120,60));

        subPanel0.setLayout(new BorderLayout(0,0));
        subPanel0.setBorder(new EmptyBorder(5,20,0,0));

        subPanel1.setLayout(new GridLayout(6,1));
        subPanel1.setBorder(new EmptyBorder(5,0,10,5));

        GridLayout gridLayout = new GridLayout(4,1,0,0);
        subPanel2.setLayout(gridLayout);
        subPanel2.setBorder(new EmptyBorder(5,5,0,0));

        marginPanel.setBorder(new EmptyBorder(5, 20, 30, 15));

        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        //Add component
        imagePanel.add(labelForIcon);
        imagePanel.add(setFontLabel(identifier));
        buttonPanel.add(button);

        subPanel0.add(imagePanel,BorderLayout.CENTER);
        subPanel0.add(buttonPanel,BorderLayout.SOUTH);

        subPanel1.add(generateTextInputPanel_reWrite("Full name (*) :",fullName,false));
        subPanel1.add(generateTextInputPanel_reWrite("Gender (*) :",gender,false));
        subPanel1.add(generateTextInputPanel_reWrite("Bank :",bank,false));
        subPanel1.add(generateTextInputPanel_reWrite("Email (*) :",email,true));
        subPanel1.add(generateTextInputPanel_reWrite("Phone (*):",phone,true));
        subPanel1.add(generateTextInputPanel_reWrite("Job-title (*):",jobTitle, true));

        subPanel2.add(generateTextInputPanel_reWrite("Room ID (*):",roomId,false));
        subPanel2.add(generateTextInputPanel_reWrite("Birthday (*):",birthday,false));
        subPanel2.add(generateTextInputPanel_reWrite("Bank Account :",bankAccountNumber,true));
        subPanel2.add(generateTextInputPanel_reWrite("Address (*):",permanentAddress));

        marginPanel.add(subPanel0);
        marginPanel.add(subPanel1);
        marginPanel.add(subPanel2);

        this.add(marginPanel);

        createPersonUpdateListener();


        this.setModal(true);
        this.setSize(1100,480);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    public JLabel setFontLabel(JTextField label){
        JLabel newLabel = new JLabel("Identifier: "+ (identifier).getText());
        label.setFont(Configs.labelFont);
        label.setFont(label.getFont().deriveFont(18.0f));
        return newLabel;
    }


    //USE FOR TEXT-AREA
    public JPanel generateTextInputPanel_reWrite(String strLabel, JTextArea originInp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 45));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));
        originInp.setLineWrap(true);
        originInp.setWrapStyleWord(true);
        originInp.setBorder(BorderFactory.createLineBorder(Color.black,1));
        originInp.setFont(originInp.getFont().deriveFont(17.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }

    //OVERLOAD
    //USE FOR TEXT-FIELD
    public JPanel generateTextInputPanel_reWrite(String strLabel, JTextField originInp, boolean bool) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 60));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));
        originInp.setEditable(bool);
        originInp.setFont(originInp.getFont().deriveFont(17.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }

    //OVERLOAD
    //USE FOR COMBO-BOX
    public JPanel generateTextInputPanel_reWrite(String strLabel, JComboBox<String> originInp, boolean bool) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 60));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));
        originInp.setEditable(bool);
        originInp.setFont(originInp.getFont().deriveFont(17.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);

        originInp.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                // get the selected item
                String selectedItem = originInp.getSelectedItem().toString();
                bankTextField.setText(selectedItem);
            }
        });

        return panel;
    }

    public void createPersonUpdateListener(){
        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("Identifier", identifier);
        inpTags.put("Email", email);
        inpTags.put("Phone", phone);
        inpTags.put("Job-Title", jobTitle);
        inpTags.put("Bank", bankTextField);
        inpTags.put("BankAccount", bankAccountNumber);

        this.button.addActionListener(RepresentativesListeners.updateByClick(inpTags,permanentAddress,this));
    }
}

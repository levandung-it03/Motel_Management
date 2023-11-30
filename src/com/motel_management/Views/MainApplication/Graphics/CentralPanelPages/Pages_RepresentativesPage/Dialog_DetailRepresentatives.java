package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class Dialog_DetailRepresentatives extends JDialog{
    private final JPanel subPanel0 = new JPanel();
    private final JPanel subPanel1 = new JPanel();
    private final JPanel subPanel2 = new JPanel();

    public Dialog_DetailRepresentatives(JFrame mainAppFrame, PersonModel person){
        super(mainAppFrame);
        this.setTitle("Details Information");
        showInformationById(person);
    }
    public void showInformationById(PersonModel person){
        JPanel marginPanel = new JPanel(new GridLayout(1,3));

        ImageIcon icon = new ImageIcon("src/com/motel_management/Assets/img/Representative.png");
        JLabel labelForIcon = new JLabel(icon);
        labelForIcon.setBorder(new EmptyBorder(30,0,5,30));
        labelForIcon.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel identifier = new JLabel("Identifier: " + person.getIdentifier());
        JLabel roomId = new JLabel("Room Id: " + person.getRoomId());
        JLabel fullName = new JLabel("Full name: " + person.getLastName() +" "+ person.getFirstName());
        JLabel birthday = new JLabel("Birth day: "+ person.getBirthday());
        JLabel phone = new JLabel("Phone: "+ person.getPhone());
        JLabel jobTitle = new JLabel("JobTitle: "+ person.getJobTitle());
        JLabel email = new JLabel("Email: "+ person.getEmail());
        JLabel bankAccountNumber = new JLabel("Account Number: "+ person.getBankAccountNumber());
        JLabel bank = new JLabel("Bank: "+ person.getBank());

        JLabel permanentAddress = new JLabel(multiLineAddress(person.getPermanentAddress()));

        //Set Gender
        JLabel gender = new JLabel("Gender: " + (person.getGender().equals("0") ? "NAM" : "NU"));


        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        subPanel0.setLayout(new BorderLayout(0,10));
        subPanel0.setBorder(new EmptyBorder(5,20,20,0));

        subPanel1.setLayout(new GridLayout(6,1));
        subPanel1.setBorder(new EmptyBorder(5,0,10,5));

        subPanel2.setLayout(new GridLayout(4,1));
        subPanel2.setBorder(new EmptyBorder(5,5,0,0));


        subPanel0.add(labelForIcon,BorderLayout.CENTER);
        subPanel0.add(setFontLabel(identifier),BorderLayout.SOUTH);

        subPanel1.add(setFontLabel(fullName));
        subPanel1.add(setFontLabel(gender));
        subPanel1.add(setFontLabel(birthday));
        subPanel1.add(setFontLabel(bank));
        subPanel1.add(setFontLabel(email));
        subPanel1.add(setFontLabel(phone));


        subPanel2.add(setFontLabel(roomId));
        subPanel2.add(setFontLabel(jobTitle));
        subPanel2.add(setFontLabel(bankAccountNumber));
        subPanel2.add(setFontLabel(permanentAddress));

        marginPanel.setBorder(new EmptyBorder(15, 20, 20, 20));
        marginPanel.add(subPanel0);
        marginPanel.add(subPanel1);
        marginPanel.add(subPanel2);
        this.add(marginPanel);

        this.setModal(true);
        this.setSize(1100,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JLabel setFontLabel(JLabel label){
        label.setFont(Configs.labelFont);
        label.setFont(label.getFont().deriveFont(18.0f));
        return label;
    }

    public String multiLineAddress(String address){
        String res = "<html> Address : ";
        res = res.concat(address + "</html>");
        return res;
    }
}

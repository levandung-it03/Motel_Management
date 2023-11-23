package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class Representatives_ShowID extends JDialog{
    public Representatives_ShowID(PersonModel person){
        this.setLayout(new GridLayout(1,3));
        showInformationById(person);
    }
    public void showInformationById(PersonModel person){
        JPanel subPanel1 = new JPanel();
        JPanel subPanel2 = new JPanel();
        JPanel subPanel3 = new JPanel();
//        JPanel subPanel4 = new JPanel();
//        JPanel subPanel5 = new JPanel();
//        JPanel subPanel6 = new JPanel();
//        JPanel subPanel7 = new JPanel();
//        JPanel subPanel8 = new JPanel();
//        JPanel subPanel9 = new JPanel();
//        JPanel subPanel10 = new JPanel();
//        JPanel subPanel11 = new JPanel();
//        JPanel subPanel12 = new JPanel();


//        JDialog mainDialogPerson = new JDialog();
//        JPanel panelShowById = new JPanel();
        ImageIcon icon = new ImageIcon("E:\\Motel_Management\\Motel_Management\\src\\com\\motel_management\\Assets\\img\\employee.png");
        JLabel labelForIcon = new JLabel(icon);

        JLabel identifier = new JLabel("Identifier: " + person.getIdentifier());
        JLabel roomId = new JLabel("Room Id: " + person.getRoomId());
        JLabel fullname = new JLabel("Full name: " + person.getLastName() +" "+ person.getFirstName());
        JLabel birthday = new JLabel("Birth day: "+ person.getBirthday());
        JLabel phone = new JLabel("Phone: "+ person.getPhone());
        JLabel jobTitle = new JLabel("JobTitle: "+ person.getJobTitle());
        JLabel permanentAddress = new JLabel("Address: "+ person.getPermanentAddress());
        JLabel email = new JLabel("Email: "+ person.getEmail());
        JLabel bankAccountNumber = new JLabel("Bank Account Number: "+ person.getBankAccountNumber());
        JLabel bank = new JLabel("Bank: "+ person.getBank());

        String tempGender; //Set Gender
        if (Objects.equals(person.getGender() , "0"))
        {tempGender = "Nam";} else {tempGender =  "Nu";}
        JLabel gender = new JLabel("Gender: "+ tempGender);

//        setBorderForJLabel(identifier);
//        setBorderForJLabel(roomId);
//        setBorderForJLabel(lastname);
//        setBorderForJLabel(birthday);
//        setBorderForJLabel(phone);
//        setBorderForJLabel(gender);
//        setBorderForJLabel(jobTitle);
//        setBorderForJLabel(permanentAddress);
//        setBorderForJLabel(email);
//        setBorderForJLabel(bankAccountNumber);
//        setBorderForJLabel(bank);

        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        subPanel2.setLayout(new GridLayout(6,1));

        subPanel3.setLayout(new GridLayout(5,1));


        subPanel2.add(setFontLabel(fullname));
        subPanel2.add(gender);
        subPanel2.add(birthday);
        subPanel2.add(setFontLabel(phone));
        subPanel2.add(bank);
        subPanel2.add(setFontLabel(bankAccountNumber));

        subPanel3.add(setFontLabel(identifier));
        subPanel3.add(setFontLabel(roomId));
        subPanel3.add(jobTitle);
        subPanel3.add(setFontLabel(permanentAddress));
        subPanel3.add(email);


//        subPanel4.add(lastname);
//        subPanel5.add(firstname);
//        subPanel6.add(birthday);
//        subPanel7.add(gender);
//        subPanel8.add(jobTitle);
//        subPanel9.add(permanentAddress);
//        subPanel10.add(email);
//        subPanel11.add(bankAccountNumber);
//        subPanel12.add(bank);





        this.add(labelForIcon);
        this.add(subPanel2);
        this.add(subPanel3);
//        panelShowById.add(subPanel4);
//        panelShowById.add(subPanel5);
//        panelShowById.add(subPanel6);
//        panelShowById.add(subPanel7);
//        panelShowById.add(subPanel8);
//        panelShowById.add(subPanel9);
//        panelShowById.add(subPanel10);
//        panelShowById.add(subPanel11);
//        panelShowById.add(subPanel12);


        this.setVisible(true);
        this.setSize(1000,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JLabel setFontLabel(JLabel label){
        label.setFont(label.getFont().deriveFont(Font.BOLD,16));
        return label;
    }
}

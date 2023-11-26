package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class Representatives_ShowID extends JDialog{
    JFrame mainAppFrame;
    JPanel subPanel0 = new JPanel();
    JPanel subPanel1 = new JPanel();
    JPanel subPanel2 = new JPanel();

    public Representatives_ShowID(JFrame mainAppFrame, PersonModel person){
        super(mainAppFrame);
        this.mainAppFrame = mainAppFrame;
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
        JLabel fullname = new JLabel("Full name: " + person.getLastName() +" "+ person.getFirstName());
        JLabel birthday = new JLabel("Birth day: "+ person.getBirthday());
        JLabel phone = new JLabel("Phone: "+ person.getPhone());
        JLabel jobTitle = new JLabel("JobTitle: "+ person.getJobTitle());
        JLabel email = new JLabel("Email: "+ person.getEmail());
        JLabel bankAccountNumber = new JLabel("Account Number: "+ person.getBankAccountNumber());
        JLabel bank = new JLabel("Bank: "+ person.getBank());

        JLabel permanentAddress = new JLabel("Address: "+ person.getPermanentAddress());

        String tempGender; //Set Gender
        if (Objects.equals(person.getGender() , "0"))
        {tempGender = "Nam";} else {tempGender =  "Nu";}
        JLabel gender = new JLabel("Gender: "+ tempGender);


        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        subPanel0.setLayout(new BorderLayout(0,10));
        subPanel0.setBorder(new EmptyBorder(5,20,20,0));

        subPanel1.setLayout(new GridLayout(5,1));
        subPanel1.setBorder(new EmptyBorder(10,0,10,5));

        subPanel2.setLayout(new GridLayout(5,1));
        subPanel2.setBorder(new EmptyBorder(10,5,10,0));


        subPanel0.add(labelForIcon,BorderLayout.CENTER);
        subPanel0.add(setFontLabel(identifier),BorderLayout.SOUTH);

        subPanel1.add(setFontLabel(fullname));
        subPanel1.add(setFontLabel(gender));
        subPanel1.add(setFontLabel(birthday));
        subPanel1.add(setFontLabel(bank));
        subPanel1.add(setFontLabel(permanentAddress));


        subPanel2.add(setFontLabel(roomId));
        subPanel2.add(setFontLabel(phone));
        subPanel2.add(setFontLabel(jobTitle));
        subPanel2.add(setFontLabel(bankAccountNumber));
        subPanel2.add(setFontLabel(email));

        marginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        marginPanel.add(subPanel0);
        marginPanel.add(subPanel1);
        marginPanel.add(subPanel2);
        this.add(marginPanel);

        this.setModal(true);
        this.setSize(1050,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JLabel setFontLabel(JLabel label){
        label.setFont(Configs.labelFont);
        label.setFont(label.getFont().deriveFont(18.0f));
        return label;
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class Representatives_ShowID {
    public Representatives_ShowID(PersonModel person){
        showInformationById(person);
    }
    public void showInformationById(PersonModel person){
        JPanel subPanel1 = new JPanel();
        JPanel subPanel2 = new JPanel();
        JPanel subPanel3 = new JPanel();
        JPanel subPanel4 = new JPanel();
        JPanel subPanel5 = new JPanel();
        JPanel subPanel6 = new JPanel();
        JPanel subPanel7 = new JPanel();
        JPanel subPanel8 = new JPanel();
        JPanel subPanel9 = new JPanel();
        JPanel subPanel10 = new JPanel();
        JPanel subPanel11 = new JPanel();
        JPanel subPanel12 = new JPanel();


        JFrame maineFramePerson = new JFrame();
        JPanel panelShowById = new JPanel();
        JLabel identifier = new JLabel("Identifier: " + person.getIdentifier());
        JLabel roomId = new JLabel("Room Id: " + person.getRoomId());
        JLabel lastname = new JLabel("Last name: " + person.getLastName());
        JLabel firstname = new JLabel("First name: "+ person.getFirstName());
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

        setBorderForJLabel(identifier);
        setBorderForJLabel(roomId);
        setBorderForJLabel(lastname);
        setBorderForJLabel(firstname);
        setBorderForJLabel(birthday);
        setBorderForJLabel(phone);
        setBorderForJLabel(gender);
        setBorderForJLabel(jobTitle);
        setBorderForJLabel(permanentAddress);
        setBorderForJLabel(email);
        setBorderForJLabel(bankAccountNumber);
        setBorderForJLabel(bank);


        panelShowById.setLayout(new GridLayout(4,3,3,3));
        panelShowById.setBackground(Color.black);
        panelShowById.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        panelShowById.setBorder(new EmptyBorder(5, 5, 5, 5));


        subPanel1.add(identifier);
        subPanel2.add(roomId);
        subPanel3.add(phone);
        subPanel4.add(lastname);
        subPanel5.add(firstname);
        subPanel6.add(birthday);
        subPanel7.add(gender);
        subPanel8.add(jobTitle);
        subPanel9.add(permanentAddress);
        subPanel10.add(email);
        subPanel11.add(bankAccountNumber);
        subPanel12.add(bank);

        panelShowById.add(subPanel1);
        panelShowById.add(subPanel2);
        panelShowById.add(subPanel3);
        panelShowById.add(subPanel4);
        panelShowById.add(subPanel5);
        panelShowById.add(subPanel6);
        panelShowById.add(subPanel7);
        panelShowById.add(subPanel8);
        panelShowById.add(subPanel9);
        panelShowById.add(subPanel10);
        panelShowById.add(subPanel11);
        panelShowById.add(subPanel12);


        maineFramePerson.add(panelShowById);
        maineFramePerson.setVisible(true);
        JOptionPane.showMessageDialog(new JPanel(), "msss", "ttt", JOptionPane.PLAIN_MESSAGE);
        maineFramePerson.setLocationRelativeTo(null);
        maineFramePerson.setSize(1000,300);
        maineFramePerson.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void setBorderForJLabel(JLabel component){
        component.setBorder(new EmptyBorder(20,5,5,5));
    }
}

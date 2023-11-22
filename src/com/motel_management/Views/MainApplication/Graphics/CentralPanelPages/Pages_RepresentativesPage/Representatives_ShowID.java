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
        JPanel subpanel1 = new JPanel();
        JPanel subpanel2 = new JPanel();
        JPanel subpanel3 = new JPanel();
        JPanel subpanel4 = new JPanel();
        JPanel subpanel5 = new JPanel();
        JPanel subpanel6 = new JPanel();
        JPanel subpanel7 = new JPanel();
        JPanel subpanel8 = new JPanel();
        JPanel subpanel9 = new JPanel();
        JPanel subpanel10 = new JPanel();
        JPanel subpanel11 = new JPanel();
        JPanel subpanel12 = new JPanel();


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

        setBorderForJlabel(identifier);
        setBorderForJlabel(roomId);
        setBorderForJlabel(lastname);
        setBorderForJlabel(firstname);
        setBorderForJlabel(birthday);
        setBorderForJlabel(phone);
        setBorderForJlabel(gender);
        setBorderForJlabel(jobTitle);
        setBorderForJlabel(permanentAddress);
        setBorderForJlabel(email);
        setBorderForJlabel(bankAccountNumber);
        setBorderForJlabel(bank);


        panelShowById.setLayout(new GridLayout(4,3,3,3));
        panelShowById.setBackground(Color.black);
        panelShowById.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        panelShowById.setBorder(new EmptyBorder(5, 5, 5, 5));


        subpanel1.add(identifier);
        subpanel2.add(roomId);
        subpanel3.add(phone);
        subpanel4.add(lastname);
        subpanel5.add(firstname);
        subpanel6.add(birthday);
        subpanel7.add(gender);
        subpanel8.add(jobTitle);
        subpanel9.add(permanentAddress);
        subpanel10.add(email);
        subpanel11.add(bankAccountNumber);
        subpanel12.add(bank);

        panelShowById.add(subpanel1);
        panelShowById.add(subpanel2);
        panelShowById.add(subpanel3);
        panelShowById.add(subpanel4);
        panelShowById.add(subpanel5);
        panelShowById.add(subpanel6);
        panelShowById.add(subpanel7);
        panelShowById.add(subpanel8);
        panelShowById.add(subpanel9);
        panelShowById.add(subpanel10);
        panelShowById.add(subpanel11);
        panelShowById.add(subpanel12);


        maineFramePerson.add(panelShowById);
        maineFramePerson.setVisible(true);
        maineFramePerson.setLocationRelativeTo(null);
        maineFramePerson.setSize(1000,300);
        maineFramePerson.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void setBorderForJlabel(JLabel component){
        component.setBorder(new EmptyBorder(20,5,5,5));
    }
}

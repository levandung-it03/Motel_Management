package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;
import com.mysql.cj.x.protobuf.MysqlxExpr;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class Controller_Representatives {
    public Controller_Representatives(){
        super();
    }

    public static String[][] getAllRepresentativesWithTableFormat() {
        ArrayList<PersonModel> result = PersonDAO.getInstance().selectAll();
        String[][] person = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            person[i][0] = result.get(i).getIdentifier();
            person[i][1] = result.get(i).getFirstName();
            person[i][2] = result.get(i).getRoomId();
            person[i][3] = result.get(i).getPhone();
            person[i][4] = result.get(i).getPermanentAddress();
            person[i][5] = "Details";
        }
        return person;
    }

    public static void showInformationById(PersonModel person){
        JFrame maineFramePerson = new JFrame();
        JPanel panelShowById = new JPanel();
        JLabel identifier = new JLabel("Identifier: " + person.getIdentifier());
        JLabel roomId = new JLabel("Room Id: " + person.getRoomId());
        JLabel lastname = new JLabel("Last name: " + person.getLastName());
        JLabel firstname = new JLabel("First name: "+ person.getFirstName());
        JLabel birthday = new JLabel("Birth day: "+ person.getBirthday());
        JLabel phone = new JLabel("Phone: "+ person.getPhone());
        JLabel gender = new JLabel("Gender: "+ person.getGender());
        JLabel jobTitle = new JLabel("JobTitle: "+ person.getJobTitle());
        JLabel permanentAddress = new JLabel("Permanent Address: "+ person.getPermanentAddress());
        JLabel email = new JLabel("Email: "+ person.getEmail());
        JLabel bankAccountNumber = new JLabel("Bank Account Number: "+ person.getBankAccountNumber());
        JLabel bank = new JLabel("Bank: "+ person.getBank());

        identifier.setBorder(new EmptyBorder(10,10,10,10));
        roomId.setBorder(new EmptyBorder(10,10,10,10));
        lastname.setBorder(new EmptyBorder(10,10,10,10));
        firstname.setBorder(new EmptyBorder(10,10,10,10));
        birthday.setBorder(new EmptyBorder(10,10,10,10));
        phone.setBorder(new EmptyBorder(10,10,10,10));
        gender.setBorder(new EmptyBorder(10,10,10,10));
        jobTitle.setBorder(new EmptyBorder(10,10,10,10));
        permanentAddress.setBorder(new EmptyBorder(10,10,10,10));
        email.setBorder(new EmptyBorder(10,10,10,10));
        bankAccountNumber.setBorder(new EmptyBorder(10,10,10,10));
        bank.setBorder(new EmptyBorder(10,10,10,10));

        panelShowById.setLayout(new GridLayout(4,3));
        panelShowById.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        panelShowById.setBorder(new EmptyBorder(20, 40, 0, 40));


        panelShowById.add(identifier);
        panelShowById.add(roomId);
        panelShowById.add(lastname);
        panelShowById.add(firstname);
        panelShowById.add(birthday);
        panelShowById.add(phone);
        panelShowById.add(gender);
        panelShowById.add(jobTitle);
        panelShowById.add(permanentAddress);
        panelShowById.add(email);
        panelShowById.add(bankAccountNumber);
        panelShowById.add(bank);


        maineFramePerson.add(panelShowById);
        maineFramePerson.setVisible(true);
        maineFramePerson.setLocationRelativeTo(null);
        maineFramePerson.setSize(1000,200);
        maineFramePerson.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
}

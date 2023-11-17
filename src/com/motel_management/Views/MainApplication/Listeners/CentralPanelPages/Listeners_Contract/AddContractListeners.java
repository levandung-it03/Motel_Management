package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.Configs;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContractListeners {
    public AddContractListeners() {
    }

    public static ActionListener addNewContractListener(HashMap<String, JTextField> inpTags,
                                                        HashMap<String, JDateChooser> dateTags,
                                                        HashMap<String, JComboBox<Object>> comboTags,
                                                        HashMap<String, Integer> maxQuantity) {
        String validateResult = validate(inpTags, dateTags, comboTags, maxQuantity);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (validateResult.equals("1")) {
                    int res = RoomDAO.getInstance().insert(new String[] {
                            inpTags.get("identifier").getText(),
                            inpTags.get("lastName").getText(),
                            inpTags.get("firstname").getText(),
                            dateFormat.format(dateTags.get("birthday").getCalendar()),
                            inpTags.get("phone").getText(),
                            inpTags.get("jobTitle").getText(),
                            inpTags.get("permanentAddress").getText(),
                            inpTags.get("email").getText(),
                            inpTags.get("creditCard").getText(),
                            Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString(),
                            inpTags.get("quantity").getText(),
                            inpTags.get("roomDeposit").getText(),
                            dateFormat.format(dateTags.get("startingDate").getCalendar()),
                            dateFormat.format(dateTags.get("endingDate").getCalendar()),
                            Objects.requireNonNull(comboTags.get("gender").getSelectedItem()).toString(),
                            Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString()
                    });
                    boolean newContractUpdated = true;

                    if (newContractUpdated) {
                        JOptionPane.showMessageDialog(new JPanel(), "New Contract was added! Open \"Contract List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);
                        inpTags.forEach((key, tag) -> {
                            tag.setText("");
                        });
                    } else {
                        JOptionPane.showMessageDialog(
                                new JPanel(),
                                "Identifier Already Existed",
                                "Notice",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            new JPanel(),
                            "Invalid Information at " + validateResult,
                            "Notice",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        };
    }

    public static String validate(HashMap<String, JTextField> inpTags, HashMap<String, JDateChooser> dateTags,
                                  HashMap<String, JComboBox<Object>> comboTags, HashMap<String, Integer> maxQuantity) {
        if (!Pattern.compile("\\d{9}").matcher(inpTags.get("identifier").getText()).matches())
            return "Identity Card";

        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]*)*$").matcher(inpTags.get("lastName").getText()).matches())
            return "Last Name";

        if (!Pattern.compile("^[A-Z][a-z]+$").matcher(inpTags.get("firstname").getText()).matches())
            return "First Name";

        try {
            if (dateTags.get("birthday").getCalendar().after((Object) Calendar.getInstance()))
                return "Birthday";
        } catch (NullPointerException e) {
            return "empty Birthday";
        }

        if (!Pattern.compile("\\d{10}").matcher(inpTags.get("phone").getText()).matches())
            return "Phone";

        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]*)*$").matcher(inpTags.get("jobTitle").getText()).matches())
            return "Job";

        if (inpTags.get("permanentAddress").getText().equals(""))
            return "Permanent Address";

        if (!inpTags.get("email").getText().equals("")) {
            // Checking "Email" if it's not empty!
            if (!Pattern.compile("^(.+)@(\\\\S+)$").matcher(inpTags.get("email").getText()).matches()) {
                return "Email";
            }
        }

        if (!inpTags.get("creditCard").getText().equals("")) {
            if (!Objects.requireNonNull(comboTags.get("bank").getSelectedItem()).toString().equals("")) {
                if (!Configs.isIntegerNumeric(inpTags.get("creditCard").getText())) {
                    return "Credit Card";
                }
            } else {
                return "empty Bank Name";
            }
        }
        if (!Objects.requireNonNull(comboTags.get("bank").getSelectedItem()).toString().equals("")) {
            if (inpTags.get("creditCard").getText().equals("")) {
                return "empty Credit Card";
            }
        }
        try {
            Object temp = Objects.requireNonNull(comboTags.get("roomId").getSelectedItem());
        } catch (NullPointerException e) { return "";}

        try {
            Integer max = maxQuantity.get(Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString());
            int quantity = Integer.parseInt(inpTags.get("quantity").getText());
            if (!(quantity > 0 && quantity <= max.intValue())) {
                return "Number Of People";
            }
        } catch (NumberFormatException e) {
            return "Number Of People";
        }

        try {
            if (Double.parseDouble(inpTags.get("roomDeposit").getText()) < 0) {
                System.out.println(Double.parseDouble(inpTags.get("roomDeposit").getText()));
                return "Deposit";
            }
        } catch (NumberFormatException e) {
            return "Deposit";
        }

        try {
            if (dateTags.get("endingDate").getCalendar().before(dateTags.get("startingDate").getCalendar()))
                return "Ended Date";
        } catch (NullPointerException e) {
            return "Empty Ended Date";
        }
        return "1";
    }

}

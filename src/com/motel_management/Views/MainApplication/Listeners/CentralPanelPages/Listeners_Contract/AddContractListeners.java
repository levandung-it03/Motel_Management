package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String validateResult = validate(inpTags, dateTags, comboTags, maxQuantity);
                if (validateResult.equals("1")) {
//                    int res = RoomDAO.getInstance().insert(new String[] {
//                            inpTags.get("roomCodeInp").getText(),
//                            "0",
//                            inpTags.get("maxQuantity").getText(),
//                            inpTags.get("defaultPrice").getText()
//                    });
                    if (true) {
//                        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
//                        StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
//                        lastRoomId.replace(0, 1, "0");
//                        StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
//                        while (idTail.length() != 3)
//                            idTail.insert(0, "0");

                        JOptionPane.showMessageDialog(new JPanel(), "New Contract was added! Open \"Contract List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);
                        inpTags.forEach((key, tag) -> {
                            tag.setText("");
                        });
                    } else {
                        JOptionPane.showMessageDialog(
                                new JPanel(),
                                "RoomCode Already Existed",
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

        if (dateTags.get("birthday").getCalendar().after((Object) Calendar.getInstance()))
            return "Birthday";

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
            if (Integer.parseInt(inpTags.get("quantity").getText()) >
                    maxQuantity.get(Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString())) {
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
            e.printStackTrace();
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

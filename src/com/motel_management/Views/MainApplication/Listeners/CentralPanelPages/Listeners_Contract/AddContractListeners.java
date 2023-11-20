package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractPage;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class AddContractListeners {
    static HashMap<String, Integer> maxQuantityList = new HashMap<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public AddContractListeners() { }

    public static JComboBox<Object> createRoomIdComboBox() {
        ArrayList<RoomModel> roomList = Controller_Room.getAllRoomWithCondition("WHERE (quantity = 0)");
        roomList.forEach(r -> {
            maxQuantityList.put(r.getRoomId(), r.getMaxQuantity());
        });
        return new JComboBox<Object>(roomList.stream().map(RoomModel::getRoomId).toArray());
    }

    public static ActionListener addNewContractListener(HashMap<String, JTextField> inpTags,
                                                        HashMap<String, JDateChooser> dateTags,
                                                        HashMap<String, JComboBox<Object>> comboTags) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String validateResult = validate(inpTags, dateTags, comboTags, maxQuantityList);

                if (validateResult.equals("1")) {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("identifier", inpTags.get("identifier").getText());
                    data.put("lastName", inpTags.get("lastName").getText());
                    data.put("firstname", inpTags.get("firstname").getText());
                    data.put("birthday", dateFormat.format(dateTags.get("birthday").getCalendar().getTime()));
                    data.put("phone", inpTags.get("phone").getText());
                    data.put("jobTitle", inpTags.get("jobTitle").getText());
                    data.put("permanentAddress", inpTags.get("permanentAddress").getText());
                    data.put("email", inpTags.get("email").getText());
                    data.put("bankAccountNumber", inpTags.get("bankAccountNumber").getText());
                    data.put("bank", Objects.requireNonNull(comboTags.get("bank").getSelectedItem()).toString());
                    data.put("roomId", Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString());
                    data.put("quantity", inpTags.get("quantity").getText());
                    data.put("roomDeposit", inpTags.get("roomDeposit").getText());
                    data.put("startingDate", dateFormat.format(dateTags.get("startingDate").getCalendar().getTime()));
                    data.put("endingDate", dateFormat.format(dateTags.get("endingDate").getCalendar().getTime()));
                    data.put("gender", Objects.requireNonNull(comboTags.get("gender")
                            .getSelectedItem())
                            .toString()
                            .equals("Men") ? "0" : "1"
                    );
                    data.put("isFamily", Objects.requireNonNull(comboTags.get("isFamily")
                            .getSelectedItem())
                            .toString()
                            .equals("NO") ? "0" : "1"
                    );
                    data.put("isRegisteredPerAddress", Objects.requireNonNull(comboTags.get("isRegisteredPerAddress")
                            .getSelectedItem())
                            .toString()
                            .equals("NO") ? "0" : "1"
                    );

                    // Add new Contract and get results.
                    int newContractUpdated = Controller_Contract.addNewContract(data);

                    if (newContractUpdated != 0) {
                        if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm This Submitting Action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                            JOptionPane.showMessageDialog(new JPanel(), "New Contract was added! Open \"Contract List\" to check it!",
                                    "Notice", JOptionPane.PLAIN_MESSAGE);

                            ContractPage.mainPage.setSelectedIndex(0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                new JPanel(),
                                "Identity Card Already Existed",
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
                                  HashMap<String, JComboBox<Object>> comboTags, HashMap<String, Integer> maxQuantityList) {
        System.out.println();
        if (!Pattern.compile("\\d{12}").matcher(inpTags.get("identifier").getText()).matches())
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
            if (!Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(inpTags.get("email").getText()).matches()) {
                return "Email";
            }
        }

        if (!inpTags.get("bankAccountNumber").getText().equals("")) {
            if (!Objects.requireNonNull(comboTags.get("bank").getSelectedItem()).toString().equals("")) {
                if (!Pattern.compile("^[0-9]{1,13}$").matcher(inpTags.get("bankAccountNumber").getText()).matches()) {
                    return "Bank Account Number";
                }
            } else {
                return "empty Bank Name";
            }
        }

        if (!Objects.requireNonNull(comboTags.get("bank").getSelectedItem()).toString().equals("")) {
            if (inpTags.get("bankAccountNumber").getText().equals("")) {
                return "empty Bank Account Number";
            }
        }

        try {
            Object temp = Objects.requireNonNull(comboTags.get("roomId").getSelectedItem());
        } catch (NullPointerException e) { return "Room Code";}

        try {
            Integer max = maxQuantityList.get(Objects.requireNonNull(comboTags.get("roomId").getSelectedItem()).toString());
            int quantity = Integer.parseInt(inpTags.get("quantity").getText());
            if (quantity != -1) {
                if (!(quantity > 0 && quantity <= max.intValue())) {
                    return "Number Of People";
                }
            }
        } catch (NumberFormatException e) {
            return "Number Of People";
        }

        try {
            if (!Pattern.compile("^[0-9]{1,9}$").matcher(inpTags.get("roomDeposit").getText()).matches()) {
                return "Deposit";
            }
        } catch (NumberFormatException e) {
            return "Deposit";
        }

        try {
            if (dateTags.get("startingDate").getCalendar().before(dateTags.get("birthday").getCalendar()))
                return "Started Date";
        } catch (NullPointerException e) {
            return "Empty Started Date";
        }

        try {
            if (dateTags.get("endingDate").getCalendar().before(dateTags.get("startingDate").getCalendar()))
                return "Ended Date";
        } catch (NullPointerException e) {
            return "Empty Ended Date";
        }

        if (Configs.calTotalMonthsBetweenStrDates(dateFormat.format(dateTags.get("startingDate").getCalendar().getTime()),
        dateFormat.format(dateTags.get("endingDate").getCalendar().getTime())) < 12
        && Objects.requireNonNull(comboTags.get("isRegisteredPerAddress").getSelectedItem()).toString().equals("YES"))
            return "Registering Permanent or Temporary Household with under 12 Months Total Contract Time!";

        return "1";
    }

}

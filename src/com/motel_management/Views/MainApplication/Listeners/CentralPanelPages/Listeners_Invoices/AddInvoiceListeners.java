package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.DataAccessObject.ElectricRangeDAO;
import com.motel_management.Models.ElectricRangeModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddInvoiceListeners {
    ArrayList<RoomModel> roomList = new ArrayList<>();

    public AddInvoiceListeners() {}

    public JComboBox<Object> createRoomIdComboBox() {
        roomList = Controller_Room.getAllRoomWithCondition("WHERE NOT (quantity = 0)");
        if (roomList.size() == 0)
            return new JComboBox<>(new String[] {""});
        return new JComboBox<Object>(roomList.stream().map(RoomModel::getRoomId).toArray());
    }

    public static void automaticallySetValueTextField(JComboBox<Object> roomId, HashMap<String, JTextField> inpTags) {
        String roomIdValue = Objects.requireNonNull(roomId.getSelectedItem()).toString();
        ArrayList<RoomModel> room = Controller_Room.getAllRoomWithCondition("WHERE roomId=\"" + roomIdValue + "\"");
        if (room.size() == 0)   return;

        inpTags.get("defaultRoomPrice").setText(Integer.toString(room.get(0).getDefaultRoomPrice()));

        InvoiceModel invoiceOfRoom = Controller_Invoices.getLastInvoice(roomIdValue);
        if (invoiceOfRoom != null) {
            if (invoiceOfRoom.getMonthPayment().equals("12")) {
                inpTags.get("paymentMonth").setText("1");
                inpTags.get("paymentYear").setText(Integer.toString(Integer.parseInt(invoiceOfRoom.getYearPayment()) + 1));
            } else {
                inpTags.get("paymentYear").setText(invoiceOfRoom.getYearPayment());
                inpTags.get("paymentMonth").setText(Integer.toString(Integer.parseInt(invoiceOfRoom.getMonthPayment()) + 1));
            }
            inpTags.get("formerElectricNumber").setText(Integer.toString(invoiceOfRoom.getNewElectricNumber()));
            inpTags.get("formerWaterNumber").setText(Integer.toString(invoiceOfRoom.getNewWaterNumber()));
            inpTags.get("garbage").setText(Integer.toString(invoiceOfRoom.getGarbage()));
            inpTags.get("wifi").setText(Integer.toString(invoiceOfRoom.getWifi()));
            inpTags.get("vehicle").setText(Integer.toString(invoiceOfRoom.getVehicle()));
        } else {
            inpTags.get("paymentYear").setText("");
            inpTags.get("paymentMonth").setText("");
            inpTags.get("formerElectricNumber").setText("");
            inpTags.get("formerWaterNumber").setText("");
            inpTags.get("garbage").setText("");
            inpTags.get("wifi").setText("");
            inpTags.get("vehicle").setText("");
        }
    }

    public static ActionListener changeInpValuesWhenRoomIdChanged(JComboBox<Object> roomId,
                                                                  HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInvoiceListeners.automaticallySetValueTextField(roomId, inpTags);
            }
        };
    }

    public static ActionListener addNewInvoicesListener(JComboBox<Object> roomId, HashMap<String, JTextField> inpTags)  {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String validateRes = AddInvoiceListeners.validate(inpTags);

                if (!validateRes.equals("1")) {
                    JOptionPane.showMessageDialog(new JPanel(), validateRes, "Notice", JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm This Submitting Action?","Confirm",
                JOptionPane.YES_NO_OPTION) != 0)
                    return;

                if (Controller_Electricity_Water.getLastElectricMaxRange() * Controller_Electricity_Water.getLastWaterMaxRange() != 0) {
                    JOptionPane.showMessageDialog(new JPanel(), "It's Not Enough Data To Calculate Water and" +
                           "Electric Price, please check Electric-Water", "Notice",JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                HashMap<String, String> data = new HashMap<>();
                data.put("roomId", Objects.requireNonNull(roomId.getSelectedItem()).toString());
                inpTags.forEach((key, tag) -> data.put(key, tag.getText()));

                int addRes = Controller_Invoices.addNewInvoice(data);
                if (addRes != 1) {
                    JOptionPane.showMessageDialog(new JPanel(),"This room has already had invoice on "
                            + data.get("paymentMonth") + "/" + data.get("paymentYear"),"Notice",JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                // Successfully Create New Invoice
                JOptionPane.showMessageDialog(new JPanel(),"Successfully Create Invoice of Room "
                        + Objects.requireNonNull(roomId.getSelectedItem()).toString(),"Notice",JOptionPane.PLAIN_MESSAGE
                );
                InvoicesPage.mainPage.setSelectedIndex(0);
            }
        };
    }

    public static String validate(HashMap<String, JTextField> inpTags) {
        try {
            if (0 > Integer.parseInt(inpTags.get("defaultRoomPrice").getText()))
                return "Invalid Default Room Price";
        } catch (NumberFormatException e) { return "Invalid Number at Default Room Price"; }

        if (!Pattern.compile("\\d{4}").matcher(inpTags.get("paymentYear").getText()).matches()
                || Integer.parseInt(inpTags.get("paymentYear").getText()) > (LocalDateTime.now().getYear() + 1))
            return "Need a number \"yyyy\" at Year Payment";

        if (!Pattern.compile("\\d{1,2}").matcher(inpTags.get("paymentMonth").getText()).matches()
                || Integer.parseInt(inpTags.get("paymentMonth").getText()) <= 0
                || Integer.parseInt(inpTags.get("paymentMonth").getText()) > 12)
            return "Need a number \"mm\" at Month Payment";

        if (!Pattern.compile("\\d{1,5}").matcher(inpTags.get("formerElectricNumber").getText()).matches())
            return "Need full 5 black digits at Old Electric Number (ex: 00545)";

        try {
            if (Integer.parseInt(inpTags.get("formerElectricNumber").getText()) < 0
            || Integer.parseInt(inpTags.get("formerElectricNumber").getText()) > 99999)
                return "Old Electric Number must in range 0 <= number <= 99999";
        } catch (NumberFormatException e) { return "Invalid Number at Old Electric Number"; }

        try {
            if (Integer.parseInt(inpTags.get("newElectricNumber").getText()) < 0
                    || Integer.parseInt(inpTags.get("newElectricNumber").getText()) > 99999)
                return "New Electric Number must in range 0 <= number <= 99999";
        } catch (NumberFormatException e) { return "Invalid Number at New Electric Number"; }

        if (Integer.parseInt(inpTags.get("newElectricNumber").getText()) < Integer.parseInt(inpTags.get("formerElectricNumber").getText()))
            return "New Electric Number must be larger than Old Number";

        try {
            if (Integer.parseInt(inpTags.get("formerWaterNumber").getText()) < 0
                    || Integer.parseInt(inpTags.get("formerWaterNumber").getText()) > 99999)
                return "Old Water Number must in range 0 <= number <= 99999";
        } catch (NumberFormatException e) { return "Invalid Number at Old Water Number"; }

        try {
            if (Integer.parseInt(inpTags.get("newWaterNumber").getText()) < 0
                    || Integer.parseInt(inpTags.get("newWaterNumber").getText()) > 99999)
                return "New Water Number must in range 0 <= number <= 99999";
        } catch (NumberFormatException e) { return "Invalid Number at New Water Number"; }

        if (Integer.parseInt(inpTags.get("newWaterNumber").getText()) < Integer.parseInt(inpTags.get("formerWaterNumber").getText()))
            return "New Water Number must be larger than Old Number";

        if (!Pattern.compile("\\d{1,8}").matcher(inpTags.get("garbage").getText()).matches())
            return "Invalid number at Garbage Fee";

        if (!Pattern.compile("\\d{1,8}").matcher(inpTags.get("wifi").getText()).matches())
            return "Invalid number at Wifi Fee";

        if (!Pattern.compile("\\d{1,8}").matcher(inpTags.get("vehicle").getText()).matches())
            return "Invalid number at Vehicle Fee";

        return "1";
    }
}
package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddInvoiceListeners {
    ArrayList<RoomModel> roomList = new ArrayList<>();

    public AddInvoiceListeners() {}

    public JComboBox<Object> createRoomIdComboBox() {
        roomList = Controller_Room.getAllRoomWithCondition("WHERE (quantity > 0)");
        return new JComboBox<Object>(roomList.stream().map(RoomModel::getRoomId).toArray());
    }
    
    public static void automaticallySetValueTextField(JComboBox<Object> roomId, HashMap<String, JTextField> inpTags) {
        String roomIdValue = Objects.requireNonNull(roomId.getSelectedItem()).toString();
        RoomModel room = Controller_Room.getAllRoomWithCondition("WHERE roomId=\"" + roomIdValue + "\"").get(0);
        inpTags.get("defaultRoomPrice").setText(Integer.toString(room.getDefaultRoomPrice()));

        ArrayList<InvoiceModel> invoicesOfRoom =
                Controller_Invoices.getInvoiceWithCondition("WHERE roomId=\"" + roomIdValue + "\"");
        if (invoicesOfRoom.size() != 0) {
            InvoiceModel invoiceOfRoom = invoicesOfRoom.get((Math.max(invoicesOfRoom.size() - 1, 0)));
            if (invoiceOfRoom.getMonthPayment().equals("12")) {
                invoiceOfRoom.setMonthPayment("1");
                invoiceOfRoom.setYearPayment(Integer.toString(Integer.parseInt(invoiceOfRoom.getYearPayment()) + 1));
            }
            inpTags.get("yearPayment").setText(invoiceOfRoom.getYearPayment());
            inpTags.get("monthPayment").setText(invoiceOfRoom.getMonthPayment());
            inpTags.get("formerElectricNumber").setText(Integer.toString(invoiceOfRoom.getCurrentElectricNumber()));
            inpTags.get("formerWaterNumber").setText(Integer.toString(invoiceOfRoom.getCurrentWaterNumber()));
            inpTags.get("garbage").setText(Integer.toString(invoiceOfRoom.getGarbage()));
            inpTags.get("wifi").setText(Integer.toString(invoiceOfRoom.getWifi()));
            inpTags.get("vehicle").setText(Integer.toString(invoiceOfRoom.getVehicle()));
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
                if (validateRes.equals("1")) {
                    if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm This Submit Action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                        JOptionPane.showMessageDialog(new JPanel(), "Submitted (Testing)", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), validateRes, "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
    
    public static String validate(HashMap<String, JTextField> inpTags) {
        if (!Pattern.compile("\\d{4}").matcher(inpTags.get("yearPayment").getText()).matches()
        || Integer.parseInt(inpTags.get("yearPayment").getText()) > (LocalDateTime.now().getYear() + 1))
            return "Need a number \"yyyy\" at Year Payment";

        if (!Pattern.compile("\\d{1,2}").matcher(inpTags.get("monthPayment").getText()).matches()
        || Integer.parseInt(inpTags.get("monthPayment").getText()) <= 0
        || Integer.parseInt(inpTags.get("monthPayment").getText()) > 12)
            return "Need a number \"mm\" at Month Payment";

        if (!Pattern.compile("\\d{5}").matcher(inpTags.get("formerElectricNumber").getText()).matches())
            return "Need full 5 black digits at Old Electric Number (ex: 00545)";

        if (!Pattern.compile("\\d{5}").matcher(inpTags.get("newElectricNumber").getText()).matches())
            return "Need full 5 black digits at New Electric Number (ex: 00545)";

        if (Integer.parseInt(inpTags.get("newElectricNumber").getText()) < Integer.parseInt(inpTags.get("formerElectricNumber").getText()))
            return "New Electric Number must be larger than Old Number";

        if (!Pattern.compile("\\d{4,5}").matcher(inpTags.get("formerWaterNumber").getText()).matches())
            return "Need full 4 or 5 black digits at Old Water Number (ex: 00545)";

        if (!Pattern.compile("\\d{4,5}").matcher(inpTags.get("newWaterNumber").getText()).matches())
            return "Need full 4 or 5 black digits at New Water Number (ex: 00545)";

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

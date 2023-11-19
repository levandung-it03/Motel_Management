package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.RoomModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AddInvoiceListeners {
    ArrayList<RoomModel> roomList = new ArrayList<>();

    public AddInvoiceListeners() {}

    public JComboBox<Object> createRoomIdComboBox() {
        roomList = Controller_Room.getAllRoomWithCondition("WHERE (quantity > 0)");
        return new JComboBox<Object>(roomList.stream().map(RoomModel::getRoomId).toArray());
    }

    public static ActionListener changeInpValuesWhenRoomIdChanged(JComboBox<Object> roomId, HashMap<String, JTextField> inpTags)  {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(Objects.requireNonNull(roomId.getSelectedItem()).toString());
                String roomIdValue = Objects.requireNonNull(roomId.getSelectedItem()).toString();
                RoomModel room = Controller_Room.getAllRoomWithCondition("WHERE roomId=\"" + roomIdValue + "\"").get(0);
                ArrayList<InvoiceModel> invoicesOfRoom =
                        Controller_Invoices.getInvoiceWithCondition("WHERE roomId=\"" + roomIdValue + "\"");
                InvoiceModel invoiceOfRoom = invoicesOfRoom.get((Math.max(invoicesOfRoom.size() - 1, 0)));

                inpTags.get("defaultRoomPrice").setText(Integer.toString(room.getDefaultRoomPrice()));
                inpTags.get("yearPayment").setText(invoiceOfRoom.getYearPayment());
                inpTags.get("monthPayment").setText(invoiceOfRoom.getMonthPayment());
                inpTags.get("formerElectricNumber").setText(invoiceOfRoom.getCurrentElectricNumber());
                inpTags.get("formerWaterNumber").setText(invoiceOfRoom.getCurrentWaterNumber());
                inpTags.get("garbage").setText(invoiceOfRoom.getGarbage());
                inpTags.get("wifi").setText(invoiceOfRoom.getWifi());
                inpTags.get("vehicle").setText(invoiceOfRoom.getVehicle());
            }
        };
    }

    public static ActionListener addNewInvoicesListener(JComboBox<Object> roomId, HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}

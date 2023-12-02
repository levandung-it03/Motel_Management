package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanel;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room.Page_RoomsMain;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Controller_Room {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Controller_Room() {
        super();
    }

    public static String[][] getRoomInfo(String[] condition) {
        // Default
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();

        // Search
        if (condition[0].equalsIgnoreCase("Search")) {
            result = RoomDAO.getInstance().selectByCondition("WHERE roomId LIKE \"%" + condition[1] + "%\"");
            if (result.isEmpty()) {
                ArrayList<PersonModel> personResult = PersonDAO.getInstance().selectByCondition("WHERE (lastName LIKE \"%" +
                        condition[1] + "%\" OR firstName LIKE \"%" + condition[1] + "%\") AND isOccupied = 1");
                if (personResult.isEmpty()) {
                    result = RoomDAO.getInstance().selectByCondition("WHERE 0");
                } else {
                    for (PersonModel personModel : personResult) {
                        result.add(RoomDAO.getInstance().selectById(personModel.getRoomId()));
                    }
                }
            }
        }
        // Filter Empty Room
        if (condition[0].equalsIgnoreCase("Empty")) {
            result = RoomDAO.getInstance().selectByCondition(condition[1]);
        }

        // Filter Unpaid Room
        if (condition[0].equalsIgnoreCase("Unpaid")) {
            result = RoomDAO.getInstance().selectByCondition("WHERE 0");
            for (RoomModel roomModel : RoomDAO.getInstance().selectAll()) {
                if (getRoomStatus(roomModel.getRoomId()) == 1) {
                    result.add(RoomDAO.getInstance().selectById(roomModel.getRoomId()));
                }
            }
        }

        String[][] rooms = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            rooms[i][0] = result.get(i).getRoomId();
            ArrayList<ContractModel> contractResult = ContractDAO.getInstance().selectByCondition("WHERE roomId=\"" +
                    result.get(i).getRoomId() + "\" AND checkedOut = 0");
            if (contractResult.isEmpty()) {
                rooms[i][1] = "Unknown";
            } else {
                PersonModel personResult = PersonDAO.getInstance().selectById(contractResult.get(0).getIdentifier());
                rooms[i][1] = personResult.getLastName() + " " + personResult.getFirstName();
            }
            rooms[i][2] = Integer.toString(result.get(i).getQuantity());
            rooms[i][3] = Integer.toString(result.get(i).getMaxQuantity());
            rooms[i][4] = Integer.toString(result.get(i).getDefaultRoomPrice());
        }
        return rooms;
    }

    public static String addNewRoom(String[] data) {
        int res = RoomDAO.getInstance().insert(data);
        if (res == 0) {
            return null;
        } else {
            ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
            StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
            lastRoomId.replace(0, 1, "0");
            StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
            while (idTail.length() != 3)
                idTail.insert(0, "0");
            return "P" + idTail;
        }
    }

    public static int updateRoom(String[] data) {
        return RoomDAO.getInstance().update(data);
    }

    public static void resetRoomStatus(String[] data) {
        RoomDAO.getInstance().resetRoomStatus(data);
    }

    public static String getLastId() {
        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
        if (roomList.isEmpty()) {
            return "P001";
        } else {
            StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
            lastRoomId.replace(0, 1, "0");
            StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
            while (idTail.length() != 3)
                idTail.insert(0, "0");
            return "P" + idTail;
        }
    }

    public static ArrayList<RoomModel> getAllRoomWithCondition(String condition) {
        return RoomDAO.getInstance().selectByCondition(condition);
    }

    public static int deleteById(String id) {
        return RoomDAO.getInstance().delete(id);
    }

    public static boolean validateCheckOut(String roomId) {
        ArrayList<InvoiceModel> roomPayment = InvoiceDAO.getInstance().selectByCondition("WHERE roomId = \"" + roomId + "\"");
        RoomModel room = RoomDAO.getInstance().selectById(roomId);
        if (room.getQuantity() == 0) {
            JOptionPane.showConfirmDialog(new Panel(), "Room is not occupied!",
                    "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        for (InvoiceModel invoiceModel : roomPayment) {
            //check if all months are paid
            if (invoiceModel.getWasPaid().equals("0")) {
                JOptionPane.showConfirmDialog(new Panel(), "Check-out failed due to unpaid payment",
                        "Notice", JOptionPane.DEFAULT_OPTION);
                return false;
            }
        }
        return true;
    }

    public static int getRoomStatus(String roomId) {
        ArrayList<InvoiceModel> roomPayment = InvoiceDAO.getInstance().selectByCondition("WHERE roomId = \"" + roomId + "\"");
        RoomModel room = RoomDAO.getInstance().selectById(roomId);
        if (room.getQuantity() == 0) {
            return 0;
        }
        for (InvoiceModel invoiceModel : roomPayment) {
            //check if all months are paid
            if (invoiceModel.getWasPaid().equals("0")) {
                return 1;
            }
        }
        return 2;
    }

    public static void validateCheckOutInfo(String roomId, JDateChooser checkOutDate, JTextArea reason,
                                            JFrame mainFrameApp, JDialog dialog){
        ArrayList<ContractModel> contractId = ContractDAO.getInstance().selectByCondition("WHERE roomId = \"" +
                roomId + "\" AND checkedOut = 0");
        try {
            if (checkOutDate.getDate().before(contractId.get(0).getStartingDate()) ||
                    checkOutDate.getDate().equals(contractId.get(0).getStartingDate())) {
                JOptionPane.showConfirmDialog(new Panel(),
                        "Check-out date must be after the starting date!",
                        "Notice", JOptionPane.DEFAULT_OPTION);
            } else {
                String checkOutId = "CK" + Configs.generateIdTail();
                String[] data = {checkOutId, contractId.get(0).getContractId(),
                        dateFormat.format(checkOutDate.getCalendar().getTime()), reason.getText()};
                String nextIdWhenSuccessfully = Controller_Checkout.addCheckOutHistory(data);
                if (nextIdWhenSuccessfully != null) {
                    JOptionPane.showConfirmDialog(new Panel(), "Successful Check-out",
                            "Notice", JOptionPane.DEFAULT_OPTION);
                    Controller_Contract.updateContractStatus(new String[]{"1", contractId.get(0).getContractId()});
                    Controller_Representatives.updatePersonStatus(new String[]{"0", contractId.get(0).getIdentifier()});
                    Controller_Room.resetRoomStatus(new String[]{"0", roomId});
                    CentralPanel.category.setComponentAt(1,new Page_RoomsMain(mainFrameApp));
                    dialog.dispose();
                }
            }
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(new JPanel(),
                    "Invalid at Check-out Date", "Notice", JOptionPane.PLAIN_MESSAGE
            );
        }
    }


}

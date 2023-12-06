package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
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
        // Filter Empty Room
        if (condition[0].equalsIgnoreCase("1")) {
            result = RoomDAO.getInstance().selectByCondition("WHERE quantity = 0");
        }

        // Filter Unpaid Room
        if (condition[0].equalsIgnoreCase("2")) {
            result = RoomDAO.getInstance().selectByCondition("WHERE 0");
            for (RoomModel roomModel : RoomDAO.getInstance().selectAll()) {
                if (getRoomStatus(roomModel.getRoomId()) == 1) {
                    result.add(RoomDAO.getInstance().selectById(roomModel.getRoomId()));
                }
            }
        }
        //Search
        ArrayList<RoomModel> temp = result;
        result = RoomDAO.getInstance().selectByCondition("WHERE 0");
        for (int i = 0; i < temp.size(); i++) {
            switch (condition[1]) {
                case "0" -> {
                    if (temp.get(i).getRoomId().contains(condition[2].toUpperCase())) result.add(temp.get(i));
                }
                case "1" -> {
                    ArrayList<PersonModel> personResult = PersonDAO.getInstance().selectByCondition("WHERE (lastName LIKE \"%" +
                            condition[2] + "%\" OR firstName LIKE \"%" + condition[2] + "%\") AND isOccupied = 1");
                    for (PersonModel personModel : personResult) {
                        if (temp.get(i).getRoomId().equalsIgnoreCase(personModel.getRoomId())) {
                            result.add(temp.get(i));
                        }
                    }
                }
                case "2" -> {
                    if (String.valueOf(temp.get(i).getQuantity()).equals(condition[2])) result.add(temp.get(i));
                }
                case "3" -> {
                    if (String.valueOf(temp.get(i).getMaxQuantity()).equals(condition[2])) result.add(temp.get(i));
                }
                case "4" -> {
                    if (String.valueOf(temp.get(i).getDefaultRoomPrice()).equals(condition[2])) result.add(temp.get(i));
                }
                default -> result = temp;
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

    public static int validateCheckOut(String roomId) {
        ArrayList<InvoiceModel> roomPayment = InvoiceDAO.getInstance().selectByCondition("WHERE roomId = \"" + roomId + "\"");
        RoomModel room = RoomDAO.getInstance().selectById(roomId);
        if (room.getQuantity() == 0) {
            return 1;
        }
        for (InvoiceModel invoiceModel : roomPayment) {
            //check if all months are paid
            if (invoiceModel.getWasPaid().equals("0")) {
                return 2;
            }
        }
        return 0;
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

    public static boolean validateCheckOutInfo(String roomId, JDateChooser checkOutDate, JTextArea reason,
                                            Frame_MainApplication mainFrameApp, JDialog dialog) {
        ContractModel contractId = getContractByRoomId(roomId);
        try {
            if (checkOutDate.getDate().before(contractId.getStartingDate()) ||
                    checkOutDate.getDate().equals(contractId.getStartingDate())) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
    public static ContractModel getContractByRoomId(String roomId){
        ArrayList<ContractModel> contractId = ContractDAO.getInstance().selectByCondition("WHERE roomId = \"" +
                roomId + "\" AND checkedOut = 0");
        return contractId.get(0);
    }


}

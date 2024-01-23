package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.*;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_Room {

    public Controller_Room() {
        super();
    }

    public static int getRoomPrice(String roomId) {
        return RoomPriceHistoryDAO.getInstance().selectCurrentRoomPriceWithRoomId(roomId);
    }

    public static HashMap<String, RoomPriceHistoryModel> getAllLastPriceOfEachRoom() {
        return RoomPriceHistoryDAO.getInstance().selectAllLastPriceOfEachRoom();
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
                    int roomPriceHistoryModel = RoomPriceHistoryDAO.getInstance().selectCurrentRoomPriceWithRoomId(temp.get(i).getRoomId());
                    if (String.valueOf(roomPriceHistoryModel).equals(condition[2])) result.add(temp.get(i));
                }
                default -> result = temp;
            }
        }

        String[][] rooms = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            rooms[i][0] = result.get(i).getRoomId();
            ArrayList<PersonModel> personResult = PersonDAO.getInstance().selectByCondition("WHERE roomId=\"" +
                    result.get(i).getRoomId() + "\" AND isOccupied = 1");
            if (personResult == null || personResult.isEmpty()) {
                rooms[i][1] = "Unknown";
            } else {
                rooms[i][1] = personResult.getFirst().getLastName() + " " + personResult.getFirst().getFirstName();
            }
            rooms[i][2] = Integer.toString(result.get(i).getQuantity());
            rooms[i][3] = Integer.toString(result.get(i).getMaxQuantity());
            rooms[i][4] = Integer.toString(getRoomPrice(result.get(i).getRoomId()));
        }
        return rooms;
    }

    public static String addNewRoom(String[] data) {
        int res = RoomDAO.getInstance().insert(new String[]{data[0],data[1],data[2]});
        int resHistory = RoomPriceHistoryDAO.getInstance().insert(new String[]{data[0], LocalDate.now().toString(),data[3]});
        if (res == 0 || resHistory == 0) {
            return null;
        } else {
            ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
            StringBuilder lastRoomId = new StringBuilder(roomList.getLast().getRoomId());
            lastRoomId.replace(0, 1, "0");
            StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
            while (idTail.length() != 3)
                idTail.insert(0, "0");
            return "P" + idTail;
        }
    }

    public static int updateRoom(String[] data) {
        return RoomDAO.getInstance().update(new String[]{data[0],data[1],data[2]})
                * RoomPriceHistoryDAO.getInstance().update(new String[]{data[0], String.valueOf(LocalDate.now()),data[3]});
    }

    public static int resetRoomStatus(String[] data) {
        return RoomDAO.getInstance().resetRoomStatus(data);
    }

    public static String getLastId() {
        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
        if (roomList.isEmpty()) {
            return "P001";
        } else {
            StringBuilder lastRoomId = new StringBuilder(roomList.getLast().getRoomId());
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
        if (ContractDAO.getInstance().selectByCondition("WHERE roomId = \""+id+"\"").isEmpty()){
            return RoomPriceHistoryDAO.getInstance().delete(id) * RoomDAO.getInstance().delete(id);
        }
        return 0;
    }

    public static int validateCheckOut(String roomId) {
        ArrayList<InvoiceModel> roomPayment = InvoiceDAO.getInstance().selectByCondition("WHERE roomId = \"" + roomId + "\"");
        RoomModel room = RoomDAO.getInstance().selectById(roomId);
        if (room.getQuantity() == 0) {
            return 1;
        }
        for (InvoiceModel invoiceModel : roomPayment) {
            //check if all months are paid
            if (!invoiceModel.getWasPaid()) {
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
            if (!invoiceModel.getWasPaid()) {
                return 1;
            }
        }
        return 2;
    }

    public static boolean validateCheckOutInfo(String roomId, JDateChooser checkOutDate, JTextArea reason,
                                               Frame_MainApplication mainFrameApp, JDialog dialog) {
        ContractModel contractId = getContractByRoomId(roomId);
        try {
            return !checkOutDate.getDate().before(contractId.getStartingDate()) &&
                    !checkOutDate.getDate().equals(contractId.getStartingDate());
        } catch (NullPointerException ignored) {
            return false;
        }
    }

    public static ContractModel getContractByRoomId(String roomId) {
        ArrayList<ContractModel> contractId = ContractDAO.getInstance().selectByCondition("WHERE roomId = \"" +
                roomId + "\" AND checkedOut = 0");
        return contractId.getFirst();
    }
    public static Object[][] getAllRoomPriceHistoryByYearWithTableFormat(String year) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<RoomPriceHistoryModel> result = RoomPriceHistoryDAO.getInstance()
                .selectByCondition("WHERE YEAR(priceRaisedDate)=\"" + year + "\"");

        Object[][] checkouts = new Object[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            checkouts[i][0] = result.get(i).getRoomId();
            checkouts[i][1] = Configs.convertStringToVNDCurrency(result.get(i).getRoomPrice());
            checkouts[i][2] = sdf.format(result.get(i).getPriceRaisedDate());
        }
        return checkouts;
    }
}
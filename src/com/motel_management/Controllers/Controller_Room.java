package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.InvoiceDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller_Room {
    public Controller_Room() {
        super();
    }

    public static String[][] getRoomInfo(String[] condition) {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectByCondition(condition[0]);
        if (result.isEmpty()){
            ArrayList<PersonModel> personResult = PersonDAO.getInstance().selectByCondition(condition[1]+
                    "AND isOccupied = 1");
            if(personResult.isEmpty()){
                JOptionPane.showConfirmDialog(new Panel(), "No rooms found matching the information", "Notice", JOptionPane.DEFAULT_OPTION);
                result = RoomDAO.getInstance().selectAll();
            }else {
                result = RoomDAO.getInstance().selectByCondition("WHERE roomId = \""+personResult.get(0).getRoomId()+"\"");
            }
        }
        String[][] rooms = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            rooms[i][0] = result.get(i).getRoomId();
            ArrayList<ContractModel> contractResult = ContractDAO.getInstance().selectByCondition("WHERE roomId=\""+
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
            return "P" + idTail.toString();
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
        ArrayList<InvoiceModel> roomPayment = InvoiceDAO.getInstance().selectByCondition("WHERE roomId = \""+roomId+"\"");
        RoomModel room = RoomDAO.getInstance().selectById(roomId);
        if (room.getQuantity() == 0){
            JOptionPane.showConfirmDialog(new Panel(), "Room is not occupied!",
                    "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        for (int i=0;i<roomPayment.size();i++){
            //check if all months are paid
            if (roomPayment.get(i).getWasPaid().equals("0")){
                JOptionPane.showConfirmDialog(new Panel(), "Check-out failed due to unpaid payment",
                        "Notice", JOptionPane.DEFAULT_OPTION);
                return false;
            }
        }
        return true;
    }

}

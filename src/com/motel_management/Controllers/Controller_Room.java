package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;

import java.util.ArrayList;

public class Controller_Room {
    public Controller_Room() {}

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

    public static String[][] getAllRoomWithTableFormat() {
        ArrayList<RoomModel> result = RoomDAO.getInstance().selectAll();
        String[][] rooms = new String[result.size()][5];
        for (int i = 0; i < result.size(); i++) {
            rooms[i][0] = result.get(i).getRoomId();
            rooms[i][1] = Integer.toString(result.get(i).getQuantity());
            rooms[i][2] = Integer.toString(result.get(i).getMaxQuantity());
            rooms[i][3] = Integer.toString(result.get(i).getDefaultRoomPrice());
            rooms[i][4] = "Delete";
        }
        return rooms;
    }
}

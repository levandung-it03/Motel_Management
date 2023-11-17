package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddRoomListeners {
    public AddRoomListeners() {}

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("A\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("roomCodeInp").getText());
                boolean isValid = matcher.matches()
                        && Configs.isIntegerNumeric(inpTags.get("maxQuantity").getText())
                        && Configs.isIntegerNumeric(inpTags.get("defaultPrice").getText());

                if (isValid) {
                    int res = RoomDAO.getInstance().insert(new String[] {
                            inpTags.get("roomCodeInp").getText(),
                            "0",
                            inpTags.get("maxQuantity").getText(),
                            inpTags.get("defaultPrice").getText()
                    });
                    if (res != 0) {
                        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
                        StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
                        lastRoomId.replace(0, 1, "0");
                        StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
                        while (idTail.length() != 3)
                            idTail.insert(0, "0");

                        JOptionPane.showMessageDialog(new JPanel(), "New room was added! Open \"Room List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);
                        inpTags.get("roomCodeInp").setText("A" + idTail);
                        inpTags.get("maxQuantity").setText("");
                        inpTags.get("defaultPrice").setText("");
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "RoomCode Already Existed", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
}

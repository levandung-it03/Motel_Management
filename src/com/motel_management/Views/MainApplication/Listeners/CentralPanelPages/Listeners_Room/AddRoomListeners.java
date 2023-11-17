package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room;

import com.motel_management.Controllers.Controller_Room;
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

    public static String getLastRoomId() {
        return Controller_Room.getLastId();
    }

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("A\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("roomIdInp").getText());
                boolean isValid = matcher.matches()
                        && Configs.isIntegerNumeric(inpTags.get("maxQuantity").getText())
                        && Configs.isIntegerNumeric(inpTags.get("defaultPrice").getText());

                if (isValid) {
                    // Call API here.
                    String nextIdWhenSuccessfully = Controller_Room.addNewRoom(new String[] {
                            inpTags.get("roomIdInp").getText(),
                            "0",
                            inpTags.get("maxQuantity").getText(),
                            inpTags.get("defaultPrice").getText()
                    });
                    if (nextIdWhenSuccessfully != null) {
                        JOptionPane.showMessageDialog(new JPanel(), "New room was added! Open \"Room List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);

                        inpTags.get("roomIdInp").setText(nextIdWhenSuccessfully);
                        inpTags.get("maxQuantity").setText("");
                        inpTags.get("defaultPrice").setText("");
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "RoomId Already Existed", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
}
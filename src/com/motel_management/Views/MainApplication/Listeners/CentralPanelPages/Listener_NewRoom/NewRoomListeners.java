package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_NewRoom;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.NewRoomPage;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewRoomListeners {
    public AddNewRoomListeners() {}

    public static String getLastRoomId() {
        return Controller_Room.getLastId();
    }

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags, NewRoomPage panel) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("P\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("roomIdInp").getText());
                boolean isValid = true;

                try {
                    isValid = matcher.matches()
                            && !Objects.equals(inpTags.get("roomIdInp").getText(), "P999")
                            && Integer.parseInt(inpTags.get("maxQuantity").getText()) > 0
                            && Integer.parseInt(inpTags.get("defaultPrice").getText()) >= 0;
                } catch (NumberFormatException e) { isValid = false; }

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
                //create onsite listener
                panel.removeAll();
                panel.createAddNewRoomsPanel();
                panel.createNewRoomsPanel();
            }
        };
    }
    public static MouseListener addPopupMenu(JPopupMenu popupMenu){
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    System.out.println(1);
                }

            }
        };
    }
}

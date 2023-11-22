package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_NewRoom;

import com.motel_management.Controllers.Controller_NewRoom;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.MainApplication.Graphics.CentralPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_NewRoom.EditRoom_Frame;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_NewRoom.NewRoomPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRoomListeners {
    public NewRoomListeners() {}

    public static String getLastRoomId() {
        return Controller_NewRoom.getLastId();
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
                    String nextIdWhenSuccessfully = Controller_NewRoom.addNewRoom(new String[] {
                            inpTags.get("roomIdInp").getText(),
                            "0",
                            inpTags.get("maxQuantity").getText(),
                            inpTags.get("defaultPrice").getText()
                    });
                    if (nextIdWhenSuccessfully != null) {
                        JOptionPane.showMessageDialog(new JPanel(), "New room was added!",
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
                CentralPanel.category.setComponentAt(7, new NewRoomPage());
            }
        };
    }
    public static ActionListener editRoom(HashMap<String, JTextField> inpTags,EditRoom_Frame frame){
        return new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                String[] data = {inpTags.get("roomId").getText(),inpTags.get("quantity").getText(),
                    inpTags.get("maxQuantity").getText(),inpTags.get("defaultPrice").getText()};
                boolean isValid = GeneralListeners.validateNewRoomTableData(inpTags);
                if(isValid){
                    if (Controller_Room.updateRoom(data) != 0) {
                        JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        CentralPanel.category.setComponentAt(7, new NewRoomPage());
                        frame.dispose();
                    }else
                        JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
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
    public static ActionListener contractMenu(){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentralPanel.category.setSelectedIndex(2);
            }
        };
    }public static ActionListener checkoutMenu(){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Your code here
            }
        };
    }
    public static ActionListener updateMenu(String roomId,String quantity,String maxQuantity, String price){

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField roomIdText = new JTextField(roomId);
                JTextField quantityText = new JTextField(quantity);
                JTextField maxQuantityText = new JTextField(maxQuantity);
                JTextField priceText = new JTextField(price);
                new EditRoom_Frame("Update",roomIdText,quantityText,maxQuantityText,priceText);
            }
        };
    }
    public static ActionListener deleteMenu(String roomId){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this room?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    if (Controller_NewRoom.deleteById(roomId)!=0) {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                        CentralPanel.category.setComponentAt(7, new NewRoomPage());
                    } else {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        };
    }
}

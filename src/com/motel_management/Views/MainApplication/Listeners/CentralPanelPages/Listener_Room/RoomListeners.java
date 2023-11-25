package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_Room;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Controllers.Controllers_Checkout;
import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water.AddElectricity_WaterPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water.Electricity_WaterPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.CheckOutRoom_Dialog;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.UpdateRoom_Dialog;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.RoomPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water.AddEWListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomListeners {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public RoomListeners() {}

    public static String getLastRoomId() {
        return Controller_Room.getLastId();
    }

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags,JFrame mainFrameApp) {

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
                CentralPanel.category.setComponentAt(1, new RoomPage(mainFrameApp,new String[] {"",""}));
            }
        };
    }
    public static ActionListener searchRoomListener(JFrame mainFrameApp, JTextField searchRoomId) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //create onsite listener
                CentralPanel.category.setComponentAt(1, new RoomPage(mainFrameApp,
                        new String[] {"WHERE roomId LIKE \"%"+searchRoomId.getText()+"%\"",
                                "WHERE lastName LIKE \"%"+searchRoomId.getText()+"%\""+
                                        " OR firstName LIKE \"%"+searchRoomId.getText()+"%\""}
                        ));

            }
        };
    }
    public static ActionListener updateRoom(HashMap<String, JTextField> inpTags, JFrame mainFrameApp ,JDialog dialog){
        return new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                String[] data = {inpTags.get("roomId").getText(),inpTags.get("quantity").getText(),
                    inpTags.get("maxQuantity").getText(),inpTags.get("defaultPrice").getText()};
                boolean isValid = GeneralListeners.validateRoomTableData(inpTags);
                if(isValid){
                    if (Controller_Room.updateRoom(data) != 0) {
                        JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        CentralPanel.category.setComponentAt(1, new RoomPage(mainFrameApp,new String[] {"",""}));
                        dialog.dispose();
                    }else
                        JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
    public static ActionListener checkOutRoom(String roomId,JDateChooser checkOutDate,JTextArea reason, JFrame mainFrameApp , JDialog dialog){
        return new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                Date currentDate = new Date();
                if(checkOutDate.getDate().equals(currentDate) && checkOutDate.getDate().before(currentDate)){
                    JOptionPane.showConfirmDialog(new Panel(), "Check-out date must be after the current date!",
                            "Notice", JOptionPane.DEFAULT_OPTION);
                }else {
                    String checkOutId = "CK" + Configs.generateIdTail();
                    ArrayList<ContractModel> contractId= ContractDAO.getInstance().selectByCondition("WHERE roomId = \""+roomId+"\"");
                    String[] data = {checkOutId,contractId.get(0).getContractId(),
                            dateFormat.format(checkOutDate.getCalendar().getTime()),reason.getText()};
                    String nextIdWhenSuccessfully = Controllers_Checkout.addCheckOutHistory(data);
                    if (nextIdWhenSuccessfully != null) {
                        JOptionPane.showConfirmDialog(new Panel(), "Successful Check-out",
                                "Notice", JOptionPane.DEFAULT_OPTION);
                        Controller_Contract.updateContractStatus(new String[]{"1",contractId.get(0).getContractId()});
                        Controller_Representatives.updatePersonStatus(new String[]{"0",contractId.get(0).getIdentifier()});
                        Controller_Room.resetRoomStatus(new String[]{"0",roomId});
                        CentralPanel.category.setComponentAt(1, new RoomPage(mainFrameApp,new String[] {"",""}));
                        dialog.dispose();
                    }
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
    }
    public static ActionListener checkoutMenu(String roomId,JFrame mainFrameApp){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Your code here
                boolean isValid = Controller_Room.validateCheckOut(roomId);
                if (isValid){
                    new CheckOutRoom_Dialog(roomId,mainFrameApp);
                }
            }
        };
    }
    public static ActionListener updateMenu(String roomId,String quantity,String maxQuantity, String price,JFrame mainFrameApp){

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField roomIdText = new JTextField(roomId);
                JTextField quantityText = new JTextField(quantity);
                JTextField maxQuantityText = new JTextField(maxQuantity);
                JTextField priceText = new JTextField(price);
                new UpdateRoom_Dialog(mainFrameApp,roomIdText,quantityText,maxQuantityText,priceText);
            }
        };
    }
    public static ActionListener deleteMenu(String roomId,JFrame mainFrameApp){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this room?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    if (Controller_Room.deleteById(roomId)!=0) {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                        CentralPanel.category.setComponentAt(1, new RoomPage(mainFrameApp,new String[] {"",""}));
                    } else {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        };
    }
}

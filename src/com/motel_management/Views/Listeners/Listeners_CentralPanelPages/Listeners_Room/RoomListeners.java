package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room;

import com.motel_management.Controllers.Controller_Checkout;
import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Models.ContractModel;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanel;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room.Dialog_CheckOutRoom;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room.Dialog_UpdateRoom;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room.Page_RoomList;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room.Page_RoomMain;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.GeneralCentralPanelListeners;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomListeners {
    public RoomListeners() {
        super();
    }

    public static String getLastRoomId() {
        return Controller_Room.getLastId();
    }

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags) {

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
                } catch (NumberFormatException e) {
                    isValid = false;
                }

                if (isValid) {
                    // Call API here.
                    String nextIdWhenSuccessfully = Controller_Room.addNewRoom(new String[]{
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
                        JOptionPane.showMessageDialog(new JPanel(), "RoomId Already Existed", "Notice",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }

    public static KeyListener searchRoomListener(Page_RoomList page) {

        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String searchSelectedItemIndex = String.valueOf(page.searchingComboBox.getSelectedIndex());
                page.remove(page.roomScrollPane);
                page.condition = new String[]{String.valueOf(page.filterComboBox.getSelectedIndex()),
                        searchSelectedItemIndex,
                        page.searchingTextField.getText()};
                page.createRoomsPanel();
                page.revalidate();
                page.repaint();
            }
        };
    }

    public static ItemListener getRoomByStatus(Page_RoomList page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                page.remove(page.roomScrollPane);
                page.condition = new String[]{String.valueOf(page.filterComboBox.getSelectedIndex()), "", ""};
                page.createRoomsPanel();
                page.revalidate();
                page.repaint();
            }
        };
    }

    public static ActionListener updateRoom(HashMap<String, JTextField> inpTags,
                                            Frame_MainApplication mainFrameApp, JDialog dialog) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String[] data = {inpTags.get("roomId").getText(), inpTags.get("quantity").getText(),
                        inpTags.get("maxQuantity").getText(), inpTags.get("defaultPrice").getText()};
                boolean isValid = GeneralCentralPanelListeners.validateRoomTableData(inpTags);
                if (isValid) {
                    if (Controller_Room.updateRoom(data) != 0 && Controller_Room.updateRoomPrice(data) != 0 ) {
                        JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice",
                                JOptionPane.PLAIN_MESSAGE);
                        CentralPanel.category.setComponentAt(1, new Page_RoomMain(mainFrameApp));
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "Update failed !", "Notice",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
    }

    public static ActionListener checkOutRoom(String roomId, JDateChooser checkOutDate, JTextArea reason,
                                              Frame_MainApplication mainFrameApp, JDialog dialog) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boolean isValid = Controller_Room.validateCheckOutInfo(roomId, checkOutDate);
                ContractModel contractId = Controller_Room.getContractByRoomId(roomId);
                if (isValid) {
                    String checkOutId = "CK" + Configs.generateIdTail();
                    String[] data = {checkOutId, contractId.getContractId(),
                            Configs.simpleDateFormat.format(checkOutDate.getCalendar().getTime()), reason.getText()};
                    String nextIdWhenSuccessfully = Controller_Checkout.addCheckOutHistory(data);
                    if (nextIdWhenSuccessfully != null) {
                        if (Controller_Contract.updateContractStatus(new String[]{"1", contractId.getContractId()}) ==0
                        || Controller_Representatives.updatePersonStatus(new String[]{"0", contractId.getIdentifier()})==0
                        || Controller_Room.resetRoomStatus(new String[]{"0", roomId})==0){
                            JOptionPane.showConfirmDialog(new Panel(), "Checkout failed",
                                    "Notice", JOptionPane.DEFAULT_OPTION);
                        }else {
                            JOptionPane.showConfirmDialog(new Panel(), "Successful Checkout",
                                    "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                        CentralPanel.category.setComponentAt(1, new Page_RoomMain(mainFrameApp));
                        dialog.dispose();
                    }
                }else{
                    JOptionPane.showConfirmDialog(new Panel(), "Checkout date must be after the starting date!",
                            "Notice", JOptionPane.DEFAULT_OPTION);
                }
            }
        };
    }

    public static MouseListener addPopupMenu(JPopupMenu popupMenu) {
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    System.out.println(1);
                }

            }
        };
    }

    public static ActionListener contractMenu() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentralPanel.category.setSelectedIndex(2);
            }
        };
    }

    public static ActionListener checkoutMenu(String roomId, Frame_MainApplication mainFrameApp) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Controller_Room.validateCheckOut(roomId) == 0) {
                    new Dialog_CheckOutRoom(roomId, mainFrameApp);
                } else if (Controller_Room.validateCheckOut(roomId) == 1) {
                    JOptionPane.showConfirmDialog(new Panel(), "Room is not occupied!",
                            "Notice", JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showConfirmDialog(new Panel(), "Checkout failed due to unpaid payment",
                            "Notice", JOptionPane.DEFAULT_OPTION);
                }
            }
        };
    }

    public static ActionListener updateMenu(String roomId, String quantity, String maxQuantity, String price,
                                            Frame_MainApplication mainFrameApp) {

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField roomIdText = new JTextField(roomId);
                JTextField quantityText = new JTextField(quantity);
                JTextField maxQuantityText = new JTextField(maxQuantity);
                JTextField priceText = new JTextField(price);
                new Dialog_UpdateRoom(mainFrameApp, roomIdText, quantityText, maxQuantityText, priceText);
            }
        };
    }

    public static ActionListener deleteMenu(String roomId, Frame_MainApplication mainFrameApp) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this room?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    if (Controller_Room.deleteById(roomId) != 0) {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice",
                                JOptionPane.DEFAULT_OPTION);
                        CentralPanel.category.setComponentAt(1, new Page_RoomMain(mainFrameApp));
                    } else {
                        JOptionPane.showConfirmDialog(new Panel(),
                                "Delete failed because the room still has contract data", "Notice",
                                JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        };
    }
}


package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_NewRoom;

import com.motel_management.Controllers.Controller_NewRoom;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_NewRoom.NewRoomListeners;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewRoomPage extends JPanel {
    public JScrollPane roomScrollPane;
    JPanel addContainer;
    JPanel roomContainer;
    JPanel tag;
    JTextField roomIdInp = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);

    JButton submitBtn;
    JPopupMenu popupMenu;

    public NewRoomPage() {
        super(new BorderLayout());
        this.createAddNewRoomsPanel();
        this.createNewRoomsPanel();
        this.createAddRoomListeners();
    }

    public void createAddNewRoomsPanel() {
        addContainer = new JPanel(new FlowLayout());
        addContainer.setPreferredSize(new Dimension(0, 80));

        this.submitBtn = InputComboPanel.generateButton("Add Room");
        addContainer.add(InputComboPanel.generateTextInputPanel("Room Code", this.roomIdInp));
        addContainer.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", this.maxQuantity));
        addContainer.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", this.defaultPrice));
        addContainer.add(this.submitBtn);

        add(addContainer, BorderLayout.NORTH);
    }

    public void createNewRoomsPanel() {
        String[][] rooms = Controller_NewRoom.getRoomInfo();
        roomContainer = new JPanel(new BorderLayout());
        JPanel overviewPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        overviewPanel.setPreferredSize(new Dimension(0, 190 * Math.ceilDiv(rooms.length,5)));
        overviewPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        overviewPanel.setOpaque(false);

        for (String[] room : rooms) {
            overviewPanel.add(generateTagPanel(room[0], room[1], room[2], room[3], room[4]));
        }

        roomContainer.add(overviewPanel,BorderLayout.NORTH);
        roomScrollPane = new JScrollPane(roomContainer);
        roomScrollPane.setPreferredSize(new Dimension(0, 0));
        roomScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(roomScrollPane, BorderLayout.CENTER);
    }

    public JPanel generateTagPanel(String roomCode,String name, String quantity,String maxQuantity, String price) {
        // Set color for tag
        tag = new JPanel(new BorderLayout());
        if (Integer.parseInt(quantity) > 0 || Integer.parseInt(quantity) == -1) {
            tag.setBackground(Configs.normalGreen);
        } else {
            tag.setBackground(Color.gray);
        }

        //Create and format panel in tag
        JLabel roomCodeTag = new JLabel(roomCode);
        roomCodeTag.setFont(roomCodeTag.getFont().deriveFont(20.0F));
        roomCodeTag.setHorizontalAlignment(0);
        roomCodeTag.setBorder(new EmptyBorder(10, 0, 0, 0));
        roomCodeTag.setForeground(Color.white);
        tag.add(roomCodeTag, BorderLayout.NORTH);
        JPanel infoPanel = new JPanel(new GridLayout(4, 0));
        infoPanel.setBorder(new EmptyBorder(0, 20, 0, 0));

        // Add information of each room
        infoPanel.add(editLabel(name,18));
        infoPanel.add(editLabel(quantity,18));
        infoPanel.add(editLabel(maxQuantity,18));
        infoPanel.add(editLabel(price,18));
        infoPanel.setOpaque(false);
        tag.add(infoPanel, BorderLayout.CENTER);

        // Create popup menu
        popupMenu = new JPopupMenu();
        JMenuItem contractMenu = new JMenuItem("Contract");
        JMenuItem checkoutMenu = new JMenuItem("Check Out");
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        contractMenu.addActionListener(NewRoomListeners.contractMenu());
        checkoutMenu.addActionListener(NewRoomListeners.checkoutMenu());
        updateMenu.addActionListener(NewRoomListeners.updateMenu(roomCode,quantity,maxQuantity,price));
        deleteMenu.addActionListener(NewRoomListeners.deleteMenu(roomCode));
        popupMenu.add(contractMenu);
        popupMenu.add(checkoutMenu);
        popupMenu.add(updateMenu);
        popupMenu.add(deleteMenu);
        tag.setComponentPopupMenu(popupMenu);
        createRoomTagListeners();

        return tag;
    }
    public JLabel editLabel (String text,float fontSize){
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setFont(label.getFont().deriveFont(fontSize));
        return label;
    }
    public void createAddRoomListeners() {
        roomIdInp.setText(NewRoomListeners.getLastRoomId());

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomIdInp", this.roomIdInp);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(NewRoomListeners.addNewRoomListener(inpTags,this));
    }
    public void createRoomTagListeners() {
        tag.addMouseListener(NewRoomListeners.addPopupMenu(popupMenu));
    }


}

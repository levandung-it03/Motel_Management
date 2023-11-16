package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.AddRoomListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AddRoomPage extends JPanel {
    JPanel container;
    JTextField roomCodeInp = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);

    JButton submitBtn;

    // Constructor
    public AddRoomPage() {
        super(new FlowLayout());
        this.createAddRoomPage();
        this.createListeners();
    }

    public void createAddRoomPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*9/20));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(InputComboPanel.generateTextInputPanel("Room Code", this.roomCodeInp));
        container.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", this.maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", this.defaultPrice));
        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
        // Automatically Set Next RoomId.
        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
        StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
        lastRoomId.replace(0, 1, "0");
        StringBuilder idTail = new StringBuilder(Integer.toString(Integer.parseInt(lastRoomId.toString()) + 1));
        while (idTail.length() != 3)
            idTail.insert(0, "0");
        roomCodeInp.setText("A" + idTail);

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomCodeInp", this.roomCodeInp);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(AddRoomListeners.addNewRoomListener(inpTags));
    }
}

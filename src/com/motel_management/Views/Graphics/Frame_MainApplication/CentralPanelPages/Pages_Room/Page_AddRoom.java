package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room.RoomListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class Page_AddRoom extends JPanel {
    JPanel container;
    JTextField roomIdInp = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);

    JButton submitBtn;

    // Constructor
    public Page_AddRoom() {
        super(new FlowLayout());
        this.createAddRoomPage();
        this.createListeners();
    }

    public void createAddRoomPage() {
        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        container.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*2/3));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(InputComboPanel.generateTextInputPanel("Room Code", this.roomIdInp));
        container.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", this.maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", this.defaultPrice));
        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
        roomIdInp.setText(RoomListeners.getLastRoomId());

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomIdInp", this.roomIdInp);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(RoomListeners.addNewRoomListener(inpTags));
    }
}

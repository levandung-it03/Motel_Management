package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.AddRoomListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
        container.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*2/3));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(InputComboPanel.generateTextInputPanel("Room Code", this.roomCodeInp));
        container.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", this.maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", this.defaultPrice));
        container.add(this.submitBtn);

        add(container);
    }

    public void createListeners() {
        roomCodeInp.setText(AddRoomListeners.getLastRoomId());

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomCodeInp", this.roomCodeInp);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(AddRoomListeners.addNewRoomListener(inpTags));
    }
}

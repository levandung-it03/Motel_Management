package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage;

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

public class AddElectricity_WaterPage extends JPanel{
    JPanel container;
    JButton submitBtn;
    JTextField roomCodeInp = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);
    public AddElectricity_WaterPage() {
        super(new GridLayout(0,2));
        setPreferredSize(new Dimension(Configs.centralPanelWidth,Configs.centralPanelHeight));
        this.createAddElectricityPage();
        this.createAddWaterPage();
        this.createListeners();
    }
    public void createAddElectricityPage() {
        JTextField roomCodeInp = new JTextField(20);
        JTextField maxQuantity = new JTextField(20);
        JTextField maxRangeValue = new JTextField(20);
        JTextField defaultPrice = new JTextField(20);
        JLabel title = new JLabel("Add Electricity");
        title.setFont(title.getFont().deriveFont(Font.BOLD,34.0f));
        container = new JPanel(new FlowLayout());
        container.setBorder(new EmptyBorder(20, Configs.centralPanelWidth/8, 0,
                Configs.centralPanelWidth/8));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(title);
        container.add(InputComboPanel.generateTextInputPanel("Water Code", roomCodeInp));
        container.add(InputComboPanel.generateTextInputPanel("Range Name", maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Max Range Value", maxRangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Price", defaultPrice));
        container.add(this.submitBtn);

        add(container);
    }
    public void createAddWaterPage() {
        JTextField roomCodeInp = new JTextField(20);
        JTextField maxQuantity = new JTextField(20);
        JTextField maxRangeValue = new JTextField(20);
        JTextField defaultPrice = new JTextField(20);
        JLabel title = new JLabel("Add Water");
        title.setFont(title.getFont().deriveFont(Font.BOLD,34.0f));
        container = new JPanel(new FlowLayout());
        container.setBorder(new EmptyBorder(20, Configs.centralPanelWidth/8, 0,
                Configs.centralPanelWidth/8));

        this.submitBtn = InputComboPanel.generateButton("Submit");

        container.add(title);
        container.add(InputComboPanel.generateTextInputPanel("Electric Code", roomCodeInp));
        container.add(InputComboPanel.generateTextInputPanel("Range Name", maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Max Range Value", maxRangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Price", defaultPrice));
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

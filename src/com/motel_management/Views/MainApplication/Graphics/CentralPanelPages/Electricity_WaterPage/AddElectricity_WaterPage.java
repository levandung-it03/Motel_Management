package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water.AddEWListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.AddRoomListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AddElectricity_WaterPage extends JPanel{
    JPanel container;
    JTextField electricId = new JTextField(20);
    JTextField electricRangeName = new JTextField(20);
    JTextField mỉnERangeValue = new JTextField(20);
    JTextField maxERangeValue = new JTextField(20);
    JTextField defaultEPrice = new JTextField(20);
    JButton submitEBtn;
    JTextField waterId = new JTextField(20);
    JTextField waterRangeName = new JTextField(20);
    JTextField mỉnWRangeValue = new JTextField(20);
    JTextField maxWRangeValue = new JTextField(20);
    JTextField defaultWPrice = new JTextField(20);
    JButton submitWBtn;
    public AddElectricity_WaterPage() {
        super(new GridLayout(0,2));
        setPreferredSize(new Dimension(Configs.centralPanelWidth,Configs.centralPanelHeight));
        this.createAddElectricityPage();
        this.createAddWaterPage();
        this.createListeners();
    }
    public void createAddElectricityPage() {



        JLabel title = new JLabel("Add Electricity");
        title.setFont(title.getFont().deriveFont(Font.BOLD,34.0f));
        container = new JPanel(new FlowLayout());
        container.setBorder(new EmptyBorder(20, Configs.centralPanelWidth/8, 0,
                Configs.centralPanelWidth/8));

        this.submitEBtn = InputComboPanel.generateButton("Submit");

        container.add(title);
        container.add(InputComboPanel.generateTextInputPanel("Range Name", electricRangeName));
        container.add(InputComboPanel.generateTextInputPanel("Min Range Value", mỉnERangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Max Range Value", maxERangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Price", defaultEPrice));
        container.add(this.submitEBtn);

        add(container);
    }
    public void createAddWaterPage() {

        JTextField maxQuantity = new JTextField(20);
        JTextField maxRangeValue = new JTextField(20);
        JTextField defaultPrice = new JTextField(20);
        JLabel title = new JLabel("Add Water");
        title.setFont(title.getFont().deriveFont(Font.BOLD,34.0f));
        container = new JPanel(new FlowLayout());
        container.setBorder(new EmptyBorder(20, Configs.centralPanelWidth/8, 0,
                Configs.centralPanelWidth/8));

        this.submitWBtn = InputComboPanel.generateButton("Submit");

        container.add(title);
        container.add(InputComboPanel.generateTextInputPanel("Range Name", waterRangeName));
        container.add(InputComboPanel.generateTextInputPanel("Min Range Value", mỉnWRangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Max Range Value", maxWRangeValue));
        container.add(InputComboPanel.generateTextInputPanel("Price", defaultWPrice));
        container.add(this.submitWBtn);

        add(container);
    }

    public void createListeners() {
        electricId.setText(AddEWListeners.getLastElectricId());
        waterId.setText(AddEWListeners.getLastWaterId());

        HashMap<String, JTextField> inpElectricTags = new HashMap<>();
        inpElectricTags.put("electricId", this.electricId);
        inpElectricTags.put("rangeName", this.electricRangeName);
        inpElectricTags.put("minERangeValue", this.mỉnERangeValue);
        inpElectricTags.put("maxERangeValue", this.maxERangeValue);
        inpElectricTags.put("defaultEPrice", this.defaultEPrice);

        HashMap<String, JTextField> inpWaterTags = new HashMap<>();
        inpWaterTags.put("waterId", this.electricId);
        inpWaterTags.put("rangeName", this.waterRangeName);
        inpWaterTags.put("minWRangeValue", this.mỉnERangeValue);
        inpWaterTags.put("maxWRangeValue", this.maxERangeValue);
        inpWaterTags.put("defaultWPrice", this.defaultEPrice);

        this.submitEBtn.addActionListener(AddEWListeners.addNewELectricListener(inpElectricTags));
        this.submitWBtn.addActionListener(AddEWListeners.addNewWaterListener(inpWaterTags));
    }
}

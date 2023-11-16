package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddRoomPage extends JPanel {

    // Constructor
    public AddRoomPage() {
        super(new FlowLayout());
        this.createAddRoomPage();
    }

    public void createAddRoomPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*9/20));

        add(InputComboPanel.generateTextInputPanel("Room Code"));
        add(InputComboPanel.generateTextInputPanel("Number Of People"));
        add(InputComboPanel.generateTextInputPanel("Maximum Quantity"));
        add(InputComboPanel.generateTextInputPanel("Default Room Price"));
        add(InputComboPanel.generateButton("Submit"));
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Views.Configs;

import javax.swing.*;
import java.awt.*;

public class AddRoomPage extends JPanel {
    // Constructor
    public AddRoomPage() {
        super();
        this.createAddRoomPage();
    }

    public void createAddRoomPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        add(new JLabel("hehe"));
    }
}

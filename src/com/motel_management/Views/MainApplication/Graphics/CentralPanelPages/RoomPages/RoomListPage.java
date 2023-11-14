package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Views.Configs;

import javax.swing.*;

public class RoomListPage extends JPanel {
    // Constructor
    public RoomListPage() {
        super();
        this.createRoomListPage();
    }

    public void createRoomListPage() {
        setPreferredSize(Configs.centralPanelWidth, Configs.centralPanelHeight);
        add(new JLabel("hehe"));
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Views.Configs;

import javax.swing.*;
import java.awt.*;

public class RoomListPage extends JPanel {
    // Constructor
    public RoomListPage() {
        super();
        this.createRoomListPage();
    }

    public void createRoomListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        add(new JLabel("hehe"));
    }
}

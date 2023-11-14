package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import javax.swing.*;

public class RoomPage extends JPanel {

    // Constructor
    public RoomPage() {
        // Set Layout Here
        super();
        this.createRoomsPanel();
    }

    public void createRoomsPanel() {
        JTabbedPane mainPage = new JTabbedPane(JTabbedPane.TOP);
        JPanel roomsListPanel = new RoomListPage();
        JPanel addRoomPanel = new AddRoomPage();

        mainPage.add("Rooms List", roomsListPanel);
        mainPage.add("Add New Room", addRoomPanel);

        roomsListPanel.add(new JLabel("List"));
        addRoomPanel.add(new JLabel("Add"));

        add(mainPage);
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoomPage extends JPanel {
    private JTabbedPane mainPage;
    private JPanel roomsListPanel;
    private JPanel addRoomPanel;

    // Constructor
    public RoomPage() {
        // Set Layout Here
        super();
        this.createRoomsPanel();
        this.createOnsiteListeners();
    }

    public void createRoomsPanel() {
        this.mainPage = new JTabbedPane(JTabbedPane.TOP);
        roomsListPanel = new RoomListPage();
        addRoomPanel = new AddRoomPage();

        roomsListPanel.setBackground(Configs.mainWhiteBackground);
        addRoomPanel.setBackground(Configs.mainWhiteBackground);

        mainPage.add("Rooms List", roomsListPanel);
        mainPage.add("Add New Room", addRoomPanel);

        add(mainPage);
    }

    public void createOnsiteListeners() {
        mainPage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainPage.getSelectedIndex() == 0) {
                    roomsListPanel = new RoomListPage();
                    mainPage.setComponentAt(0, roomsListPanel);
                } else {
                    addRoomPanel = new AddRoomPage();
                    mainPage.setComponentAt(1, addRoomPanel);
                }
            }
        });
    }
}

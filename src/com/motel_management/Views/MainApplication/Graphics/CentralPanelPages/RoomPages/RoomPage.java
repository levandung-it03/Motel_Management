package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoomPage extends JPanel {
    private JTabbedPane mainPage;
    private static JPanel roomsListPanel;
    private static JPanel addRoomPanel;

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
                System.out.println(mainPage.getSelectedIndex());
                if (mainPage.getSelectedIndex() == 0) {
                    RoomPage.roomsListPanel = new RoomListPage();
                    mainPage.setComponentAt(0, RoomPage.roomsListPanel);
                } else {
                    RoomPage.addRoomPanel = new AddRoomPage();
                    mainPage.setComponentAt(1, RoomPage.addRoomPanel);
                }
            }
        });
    }
}

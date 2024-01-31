package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_RoomMain extends JPanel{
    private JTabbedPane mainTabbedPane;
    private JPanel roomsListPanel;
    private JPanel addRoomPanel;
    private JPanel roomPriceHistoryPanel;
    private final Frame_MainApplication mainFrameApp;

    // Constructor
    public Page_RoomMain(Frame_MainApplication mainFrameApp) {
        // Set Layout Here
        super();
        this.mainFrameApp = mainFrameApp;
        this.createRoomsPanel();
        this.createOnsiteListeners();
    }

    public void createRoomsPanel() {
        mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        roomsListPanel = new Page_RoomList(mainFrameApp);
        addRoomPanel = new Page_AddRoom();
        roomPriceHistoryPanel = new Page_RoomPriceHistory();

        roomsListPanel.setBackground(Configs.mainWhiteBackground);
        addRoomPanel.setBackground(Configs.mainWhiteBackground);
        roomPriceHistoryPanel.setBackground(Configs.mainWhiteBackground);

        mainTabbedPane.add("Rooms List", roomsListPanel);
        mainTabbedPane.add("Add New Room", addRoomPanel);
        mainTabbedPane.add("Room Price History", roomPriceHistoryPanel);

        add(mainTabbedPane);
    }

    public void createOnsiteListeners() {
        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainTabbedPane.getSelectedIndex() == 0) {
                    roomsListPanel = new Page_RoomList(mainFrameApp);
                    mainTabbedPane.setComponentAt(0, roomsListPanel);
                } else if (mainTabbedPane.getSelectedIndex() == 1) {
                    addRoomPanel = new Page_AddRoom();
                    mainTabbedPane.setComponentAt(1, addRoomPanel);
                }else {
                    roomPriceHistoryPanel = new Page_RoomPriceHistory();
                    mainTabbedPane.setComponentAt(2, roomPriceHistoryPanel);
                }
            }
        });
    }
}

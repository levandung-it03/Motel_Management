package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_RoomMain extends JPanel{
    private JTabbedPane mainTabbedPane;
    private JPanel roomsListPanel;
    private JPanel addRoomPanel;
    private final Frame_MainApplication mainFrameApp;
    //Getter
    public JTabbedPane getMainTabbedPane() { return mainTabbedPane; }

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
        roomsListPanel = new Page_RoomList(mainFrameApp,this);
        addRoomPanel = new Page_AddRoom();

        roomsListPanel.setBackground(Configs.mainWhiteBackground);
        addRoomPanel.setBackground(Configs.mainWhiteBackground);

        mainTabbedPane.add("Rooms List", roomsListPanel);
        mainTabbedPane.add("Add New Room", addRoomPanel);

        add(mainTabbedPane);
    }

    public void createOnsiteListeners() {
        Page_RoomMain _this = this;
        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainTabbedPane.getSelectedIndex() == 0) {
                    roomsListPanel = new Page_RoomList(mainFrameApp,_this);
                    mainTabbedPane.setComponentAt(0, roomsListPanel);
                } else {
                    addRoomPanel = new Page_AddRoom();
                    mainTabbedPane.setComponentAt(1, addRoomPanel);
                }
            }
        });
    }
}

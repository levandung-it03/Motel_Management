package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners;

import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AddRoomListeners {
    static ActionListener actionListener;
    public AddRoomListeners() {}

    public static ActionListener addNewRoomListener(HashMap<String, JTextField> inpTags) {
        return GeneralListeners.addNewDataByActionListener(inpTags);
    }
}

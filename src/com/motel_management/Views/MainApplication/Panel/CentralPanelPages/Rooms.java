package com.motel_management.Views.MainApplication.Panel.CentralPanelPages;

import javax.swing.*;

public class Rooms extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Rooms(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createRoomsPanel();
    }

    public void createRoomsPanel() {
        add(new JLabel("This is Rooms!"));
    }
}

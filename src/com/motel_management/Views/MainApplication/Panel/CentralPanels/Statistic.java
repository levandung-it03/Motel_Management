package com.motel_management.Views.MainApplication.Panel.CentralPanels;
import javax.swing.*;

public class Statistic extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public Statistic(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super();
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createStatisticPanel();
    }

    public void createStatisticPanel() {
        add(new JLabel("This is Statistic!"));
    }
}

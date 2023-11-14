package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
import javax.swing.*;

public class StatisticPage extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public StatisticPage(int centralPanelWidth, int centralPanelHeight) {
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

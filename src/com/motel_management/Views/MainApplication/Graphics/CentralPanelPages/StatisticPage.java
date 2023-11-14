package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
import javax.swing.*;

public class StatisticPage extends JPanel {
    // Constructor
    public StatisticPage() {
        // Set Layout Here
        super();
        this.createStatisticPanel();
    }

    public void createStatisticPanel() {
        add(new JLabel("This is Statistic!"));
    }
}

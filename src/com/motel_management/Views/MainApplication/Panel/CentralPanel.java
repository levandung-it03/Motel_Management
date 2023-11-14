package com.motel_management.Views.MainApplication.Panel;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;

    public CentralPanel(int fullWidth, int fullHeight) {
        super();
        this.centralPanelWidth = (int) (fullWidth * 6) /7;
        this.centralPanelHeight = (int) (fullHeight * 10) /11;
        this.createCentralPanel();
    }

    public void createCentralPanel() {
        setPreferredSize(new Dimension(centralPanelWidth, centralPanelHeight));
    }
}

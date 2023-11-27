package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Statistic;

import javax.swing.*;
import java.awt.*;

public class DetailStatistic_Dialog extends JDialog {
    public DetailStatistic_Dialog(Frame mainFrameApp) {
        super(mainFrameApp,"Detail Statistic");
        createDetailDialog();
    }
    public void createDetailDialog(){
        this.setModal(true);
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);



        this.setVisible(true);
    }
}

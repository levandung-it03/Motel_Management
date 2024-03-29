package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Statistic;

import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Statistic.Dialog_DetailStatistic;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StatisticListeners {
    public StatisticListeners() {
    }
    public static MouseListener getDetailProfit(JFrame mainFrameApp,JTable table){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());
                if (clickedColumn == 3 && clickedRow!= 5) {
                    new Dialog_DetailStatistic(mainFrameApp,Integer.parseInt(table.getValueAt(clickedRow,0).toString()));
                }

            }
        };
    }
}

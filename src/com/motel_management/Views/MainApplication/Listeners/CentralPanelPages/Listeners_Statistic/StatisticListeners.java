package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Statistic;

import com.motel_management.Views.MainApplication.Graphics.CentralPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.StatisticPage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class StatisticListeners {
    public StatisticListeners() {
    }
    public static MouseListener getPreviousYear(int year){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CentralPanel.category.setComponentAt(0,new StatisticPage(year-1));
            }
        };
    }
    public static MouseListener getNextYear(int year){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (year>=LocalDate.now().getYear()){
                    JOptionPane.showMessageDialog(new JPanel(), "This is the current year!");
                }else {
                    CentralPanel.category.setComponentAt(0,new StatisticPage(year+1));
                }
            }
        };
    }
}

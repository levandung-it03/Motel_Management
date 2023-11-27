package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_Statistic;

import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesOfRoomDialog;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Statistic.DetailStatistic_Dialog;

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
                new DetailStatistic_Dialog(mainFrameApp,Integer.parseInt(table.getValueAt(table.rowAtPoint(e.getPoint()),0).toString()));
            }
        };
    }
    public static MouseListener getNextYear(int year){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        };
    }
}

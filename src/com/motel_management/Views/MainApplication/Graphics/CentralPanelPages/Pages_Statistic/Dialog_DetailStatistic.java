package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Statistic;

import com.motel_management.Controllers.Controller_Statistic;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dialog_DetailStatistic extends JDialog {
    DefaultTableModel defaultStatisticModel;
    JTable statisticTable;
    JScrollPane statisticScrollPane;
    int year;
    public Dialog_DetailStatistic(Frame mainFrameApp, int year) {
        super(mainFrameApp,"Detail Statistic");
        this.year = year;
        createDetailDialog();
    }
    public void createDetailDialog(){
        this.setModal(true);
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JPanel revenuePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Monthly Revenue("+year+")");
        title.setFont(title.getFont().deriveFont(Font.BOLD,29));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));

        revenuePanel.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        Object[][] revenue = Controller_Statistic.getMonthStatistic(year);
        String[] columns = {"Month","Revenue","Profit"};
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(revenue, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultStatisticModel = tableAsList.getDefaultModel();
        this.statisticTable = tableAsList.getTable();
        this.statisticScrollPane = tableAsList.getScrollPane();

        // Resize several Columns.
        this.statisticTable.getColumnModel().getColumn(0).setPreferredWidth(20);

        statisticScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        revenuePanel.add(statisticScrollPane);
        add(revenuePanel);
        this.setVisible(true);
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class TableAsList {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultModel;

    // Constructor
    public TableAsList(DefaultTableModel defaultModel) {
        this.createTableAsList(defaultModel);
    }

    public void createTableAsList(DefaultTableModel defaultModel) {

        // Create a Table Model (with IDs Unchangeable).
        this.setDefaultModel(defaultModel);

        // Make Table Model automatically update if DB is changed.
        this.defaultModel.fireTableDataChanged();

        // Create Table
        this.setTable(new JTable(this.defaultModel));
        table.setBorder(new LineBorder(Configs.blackTextColor, 1, true));

        // Automatically unfocused with blur action.
        table.putClientProperty("terminateEditOnFocusLost", true);

        // Set Header (Column Name) Font.
        JTableHeader header = table.getTableHeader();
        header.setFont(Configs.labelFont);
        table.setFont(Configs.labelFont);

        // Set Table size.
        table.setRowHeight(30);

        // Make all Columns align horizontally.
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value.equals(Integer.MAX_VALUE)) {
                    value = "Unlimited";
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setVerticalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        // Create ScrollPane to Cover JTable.
        this.setScrollPane(new JScrollPane(table));

        // Get View, Delete Button Indices.
        int viewBtnInd = -1, deleteBtnInd = -1, updateBtnInd = -1;
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).toUpperCase().contains("UPDATE")) {
                changeCellColor(table, i, new Color(75, 217, 72));
            }
            if (table.getColumnName(i).toUpperCase().contains("DETAIL")) {
                changeCellColor(table, i, new Color(126, 170, 255));
            }
            if (table.getColumnName(i).toUpperCase().contains("DELETE")) {
                changeCellColor(table, i, new Color(250, 93, 93));
            }
        }
    }

    public static void changeCellColor(JTable table, int columnInd, Color color) {
        table.getColumnModel().getColumn(columnInd).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                this.setHorizontalAlignment(JLabel.CENTER);
                this.setBackground(color);
                return this;
            }
        });
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getDefaultModel() {
        return defaultModel;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void setDefaultModel(DefaultTableModel defaultModel) {
        this.defaultModel = defaultModel;
    }

}
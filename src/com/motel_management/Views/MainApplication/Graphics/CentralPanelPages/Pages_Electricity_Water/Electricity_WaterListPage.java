package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water;


import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water.EWListListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Electricity_WaterListPage extends JPanel {
    public JTable electricTable;
    public JTable waterTable;
    public JScrollPane roomScrollPane;
    public DefaultTableModel defaultElectricTable;
    public DefaultTableModel defaultWaterTable;
    public Object[][] electricTableData;
    public Object[][] waterTableData;

    public Electricity_WaterListPage() {
        super(new GridLayout(0, 2));
        this.createEWListPage();
        this.createListeners();
        this.saveCurrentElectricTableData(electricTable);
        this.saveCurrentWaterTableData(waterTable);
    }

    public void createEWListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        add(createElectricPanel());
        add(createWaterPanel());
    }

    public JPanel createElectricPanel() {
        JPanel ElectricPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Electric");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        ElectricPanel.add(title, BorderLayout.NORTH);

        // Prepare Date to generate Table.

        String[][] electrics = Controller_Electricity_Water.getElectricList();
        String[] columns = {"ID","Range", "Min Value", "Max Value", "Price", "Delete"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(electrics, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        this.defaultElectricTable = tableAsList.getDefaultModel();
        this.electricTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, 10));

        // Resize several Columns.
        this.electricTable.getColumnModel().getColumn(0).setPreferredWidth(30);

        ElectricPanel.add(roomScrollPane);
        return ElectricPanel;
    }

    public JPanel createWaterPanel() {
        JPanel ElectricPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Water");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        ElectricPanel.add(title, BorderLayout.NORTH);

        // Prepare Date to generate Table.
        String[][] waters = Controller_Electricity_Water.getWaterList();
        String[] columns = {"ID","Range", "Min Value", "Max Value", "Price", "Delete"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(waters, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        this.defaultWaterTable = tableAsList.getDefaultModel();
        this.waterTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, 10));

        // Resize several Columns.
        this.waterTable.getColumnModel().getColumn(0).setPreferredWidth(30);

        ElectricPanel.add(roomScrollPane);
        return ElectricPanel;
    }

    public void createListeners() {
        // Add Changing Cells Value Action.
        electricTable.getModel().addTableModelListener(EWListListeners.cellElectricValueUpdated(this));
        waterTable.getModel().addTableModelListener(EWListListeners.cellWaterValueUpdated(this));

        // Tách table ra rồi nhét vô đi
        // Add Clicking Delete Electric Button Action.
        electricTable.addMouseListener(EWListListeners.getDeleteCellByMouseListenerOfElectric(this.defaultElectricTable, this.electricTable));
        waterTable.addMouseListener(EWListListeners.getDeleteCellByMouseListenerOfWater(this.defaultWaterTable, this.waterTable));
}

    public void saveCurrentElectricTableData(JTable table) {
        // Copy Data from Table.
        this.electricTableData = new Object[table.getRowCount()][table.getColumnCount() - 1];
        for (int row = 0; row < table.getRowCount(); row++)
            for (int col = 0; col < table.getColumnCount() - 1; col++)
                this.electricTableData[row][col] = table.getValueAt(row, col);
    }
    public void saveCurrentWaterTableData(JTable table) {
        // Copy Data from Table.
        this.waterTableData = new Object[table.getRowCount()][table.getColumnCount() - 1];
        for (int row = 0; row < table.getRowCount(); row++)
            for (int col = 0; col < table.getColumnCount() - 1; col++)
                this.waterTableData[row][col] = table.getValueAt(row, col);
    }
}

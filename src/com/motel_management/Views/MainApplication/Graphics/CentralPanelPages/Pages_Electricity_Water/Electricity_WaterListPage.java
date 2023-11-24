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
        super(new GridLayout(2,0));
        this.createEWListPage();
        this.createListeners();
        this.saveNewElectricTableData(electricTable);
        this.saveNewWaterTableData(waterTable);
    }

    public void createEWListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        add(createElectricPanel());
        add(createWaterPanel());
    }

    public JPanel createElectricPanel() {
        JPanel ElectricPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Electricity");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        ElectricPanel.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.

        String[][] electrics = Controller_Electricity_Water.getElectricList();
        String[] columns = {"Electric ID", "Range Name", "Min Value", "Max Value", "Price(VND/kWh)", "Delete Button"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(electrics, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 2 && column != 3;
            }
        });
        this.defaultElectricTable = tableAsList.getDefaultModel();
        this.electricTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 50, 0, 50));

        // Resize several Columns.
        this.electricTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.electricTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.electricTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        this.electricTable.getColumnModel().getColumn(3).setPreferredWidth(40);


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
        String[] columns = {"Water ID", "Range Name", "Min Value", "Max Value",
                Controller_Electricity_Water.checkRegion(), "Delete Button"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(waters, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 2 && column != 3;
            }
        });
        this.defaultWaterTable = tableAsList.getDefaultModel();
        this.waterTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 50, 0, 50));

        // Resize several Columns.
        this.waterTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.waterTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.waterTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        this.waterTable.getColumnModel().getColumn(3).setPreferredWidth(40);

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

    public void saveNewElectricTableData(JTable table) {
        // Copy Data from Table.
        this.electricTableData = new Object[table.getRowCount()][table.getColumnCount() - 1];
        for (int row = 0; row < table.getRowCount(); row++)
            for (int col = 0; col < table.getColumnCount() - 1; col++)
                this.electricTableData[row][col] = table.getValueAt(row, col);
    }

    public void saveNewWaterTableData(JTable table) {
        // Copy Data from Table.
        this.waterTableData = new Object[table.getRowCount()][table.getColumnCount() - 1];
        for (int row = 0; row < table.getRowCount(); row++)
            for (int col = 0; col < table.getColumnCount() - 1; col++)
                this.waterTableData[row][col] = table.getValueAt(row, col);
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage;


import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water.EWListListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Electricity_WaterListPage extends JPanel {
    public JTable electricTable;
    public JTable waterTable;
    public JScrollPane roomScrollPane;
    public DefaultTableModel defaultTable;
    public Object[][] tableData;

    public Electricity_WaterListPage() {
        super(new GridLayout(0, 2));
        this.createEWListPage();
        this.createListeners();
        this.saveCurrentTableData(electricTable);
        this.saveCurrentTableData(waterTable);
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
        TableAsList tableAsList = new TableAsList(electrics, columns);
        this.defaultTable = tableAsList.getDefaultModel();
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
        TableAsList tableAsList = new TableAsList(waters, columns);
        this.defaultTable = tableAsList.getDefaultModel();
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
        electricTable.getModel().addTableModelListener(EWListListeners.cellValueUpdated(this,electricTable));
        waterTable.getModel().addTableModelListener(EWListListeners.cellValueUpdated(this,waterTable));

        // Tách table ra rồi nhét vô đi
        // Add Clicking Delete Electric Button Action.
        electricTable.addMouseListener(EWListListeners.getDeleteCellByMouseListenerOfElectric(this.defaultTable, this.electricTable));
        waterTable.addMouseListener(EWListListeners.getDeleteCellByMouseListenerOfWater(this.defaultTable, this.waterTable));
    }

    public void saveCurrentTableData(JTable table) {
        // Copy Data from Table.
        this.tableData = new Object[table.getRowCount()][table.getColumnCount() - 1];
        for (int row = 0; row < table.getRowCount(); row++)
            for (int col = 0; col < table.getColumnCount() - 1; col++)
                this.tableData[row][col] = table.getValueAt(row, col);
    }
}

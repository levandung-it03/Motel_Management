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
    public JTable table;
    public JScrollPane roomScrollPane;
    public DefaultTableModel defaultTable;
    public Object[][] tableData;

    public Electricity_WaterListPage() {
        super(new GridLayout(0, 2));
        this.createEWListPage();
        this.createListeners();
        this.saveCurrentTableData();
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
        String[] columns = {"Electric Range", "Min Value", "Max Value", "Price", "Delete"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(electrics, columns);
        this.defaultTable = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, 10));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(120);

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
        String[] columns = {"Water Range", "Min Value", "Max Value", "Price", "Delete"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(waters, columns);
        this.defaultTable = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, 10));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        ElectricPanel.add(roomScrollPane);
        return ElectricPanel;
    }

    public void createListeners() {
        // Add Changing Cells Value Action.
        table.getModel().addTableModelListener(EWListListeners.cellValueUpdated(this));

        // Add Clicking Delete Button Action.
//        table.addMouseListener(GeneralListeners.getCustomDeleteButtonMouseAdapter(this.defaultTable, this.table));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        this.tableData = new Object[this.table.getRowCount()][this.table.getColumnCount() - 1];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount() - 1; col++)
                this.tableData[row][col] = this.table.getValueAt(row, col);
    }
}

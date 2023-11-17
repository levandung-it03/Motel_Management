package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.ContractListListeners;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContractListPage extends JPanel {
    public JTable table;
    public JScrollPane contractScrollPane;
    public DefaultTableModel defaultTable;
    public Object[][] tableData;

    // Constructor
    public ContractListPage() {
        super(new GridLayout());
        this.createContractListPage();
        this.createListeners();
        this.saveCurrentTableData();
    }

    public void createContractListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Prepare Date to generate Table.
        String[][] contracts = Controller_Contract.getAllContractWithTableFormat();
        String[] columns = {"Contract Code", "Representative Identity", "Room Code", "Room Deposit", "Started Date",
                "Ended Date", "Delete Button"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(contracts, columns);
        this.defaultTable = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.contractScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.contractScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(25);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(40);

        // Add ScrollPane into CentralPanel/Contract.
        add(contractScrollPane);
    }

    public void createListeners() {
        // Add Changing Cells Value Action.
        table.getModel().addTableModelListener(ContractListListeners.cellValueUpdated(this));

        // Add Clicking Delete Button Action.
        table.addMouseListener(ContractListListeners.getCustomDeleteButtonMouseAdapter(this.defaultTable, this.table));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        this.tableData = new Object[this.table.getRowCount()][this.table.getColumnCount() - 1];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount() - 1; col++)
                this.tableData[row][col] = this.table.getValueAt(row, col);
    }
}
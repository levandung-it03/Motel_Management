package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InvoicesListPage extends JPanel {
    public JTable table;
    public JScrollPane invoiceScrollPane;
    public DefaultTableModel defaultModel;
    public Object[][] tableData;

    // Constructor
    public InvoicesListPage() {
        super(new GridLayout());
        this.createInvoicesListPage();
        this.createListeners();
        this.saveCurrentTableData();
    }

    public void createInvoicesListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Prepare Date to generate Table.
        String[][] invoices = Controller_Invoices.getAllInvoicesWithTableFormat();
        String[] columns = {"Invoice Code", "Representative Identity", "Room Code", "Quantity", "Room Deposit",
                "Started Date", "Ended Date", "Delete Button"};

        // Generate Table.
        // Make All Value Unchangeable.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(invoices, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.invoiceScrollPane = tableAsList.getScrollPane();


        // Margin Table.
        this.invoiceScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(80);

        // Add ScrollPane into CentralPanel/Invoices.
        add(invoiceScrollPane);
    }

    public void createListeners() {  }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount() - 1];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount() - 1; col++)
                tableData[row][col] = this.table.getValueAt(row, col);
    }
}

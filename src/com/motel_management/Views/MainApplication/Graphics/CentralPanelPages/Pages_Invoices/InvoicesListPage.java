package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InvoicesListPage extends JPanel {
    public JTable table;
    public JScrollPane invoiceScrollPane;
    public DefaultTableModel defaultModel;
    public Object[][] tableData;

    // Constructor
    public InvoicesListPage() {
        super(new BorderLayout());
        this.createInvoicesListPage();
        this.createListeners();
        this.saveCurrentTableData();
    }

    public void createInvoicesListPage() {
        JPanel title = new JPanel();
        JLabel titleLabel = new JLabel("Invoices");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 34.0f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        title.add(titleLabel);
        add(title, BorderLayout.NORTH);

        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Prepare Date to generate Table.
        String[][] invoices = Controller_Invoices.getAllInvoicesWithTableFormat();
        String[] columns = { "Room Id", "Invoices Detail", "Last Invoice", "Month Payment", "Year Payment", "Date Created", "Total",
                "Was Paid", "Update"};

        // Generate Table.
        // Make All Value Unchangeable.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(invoices, columns) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.invoiceScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.invoiceScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(33);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(65);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(63);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(63);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(8).setPreferredWidth(53);

        // Add ScrollPane into CentralPanel/Invoices.
        add(invoiceScrollPane, BorderLayout.CENTER);
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

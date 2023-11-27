package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.ContractListListeners;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ContractListPage extends JPanel {
    public JTable table;
    public JScrollPane contractScrollPane;
    public DefaultTableModel defaultModel;
    public Object[][] tableData;

    public JComboBox<String> filterComboBox;
    public JTextField searchingTextField = new JTextField();
    public JComboBox<String> searchingComboBox;

    // Constructor
    public ContractListPage() {
        super(new BorderLayout());
        this.createContractListPage();
        this.createListeners();
        this.saveCurrentTableData();
    }

    public void createContractListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));
        // Create Year ComboBox
        // Tools
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 95));
        tools.setBorder(new EmptyBorder(30, 15, 0, 15));

        // Filter
        int currentYear = LocalDateTime.now().getYear();
        this.filterComboBox = new JComboBox<String>(new String[] {
                "Now Occupied Rooms",
                Integer.toString(currentYear),
                Integer.toString(currentYear - 1),
                Integer.toString(currentYear - 2),
                Integer.toString(currentYear - 3),
                Integer.toString(currentYear - 4),
        });
        JPanel filterComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Filter With Starting Year", this.filterComboBox);
        tools.add(filterComboBoxContainer, BorderLayout.EAST);

        // Search
        this.searchingComboBox = new JComboBox<String>(new String[] {
                "Room",
                "Representative Card",
                "Name",
                "Room Deposit",
                "Registered Temp Residence",
                "Started Date",
                "Ended Date"
        });
        JPanel searchingComboBoxContainer = InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);

        JPanel searchingContainer = new JPanel(new BorderLayout());
        JPanel searchingTextField = InputComboPanel.generateTextInputPanel("Searching (Allow dd/, /MM/ or yyyy)", this.searchingTextField);
        searchingTextField.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.27), 65));

        searchingContainer.add(searchingComboBoxContainer, BorderLayout.WEST);
        searchingContainer.add(searchingTextField, BorderLayout.EAST);
        tools.add(searchingContainer, BorderLayout.WEST);

        // Prepare Date to generate Table.
        String[] columns = {"Room", "Representative Card", "Name", "Room Deposit", "Registered Temp Residence",
                "Started Date", "Ended Date", "Delete Button"};
        String[][] contracts = Controller_Contract.getAllValidContractWithTableFormat();

        // Create Table
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(contracts, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.contractScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.contractScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(5);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(120);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(20);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(190);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(50);

        // Add ScrollPane into CentralPanel/Contract.
        add(tools, BorderLayout.NORTH);
        add(contractScrollPane, BorderLayout.CENTER);
    }

    public void createListeners() {
        // Add Clicking Delete Button Action.
        table.addMouseListener(ContractListListeners.getDeleteCellByMouseListener(this.defaultModel, this.table, this));

        // Searching Action
//        this.searchingTextField.addActionListener(ContractListListeners.searchContract(this));
        this.searchingTextField.addKeyListener(ContractListListeners.searchContract(this));

        // Get Contracts By Year
        this.filterComboBox.addActionListener(ContractListListeners.getContractsByYear(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col).toString();
    }
}

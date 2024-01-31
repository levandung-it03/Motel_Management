package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Graphics.GeneralComponents.TableAsList;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Contract.ContractListListeners;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDateTime;

public class Page_ContractList extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    private JTable table;
    private DefaultTableModel defaultModel;
    private Object[][] tableData;

    private final JTextField searchingTextField = new JTextField();
    private JComboBox<String> filterComboBox;
    private JComboBox<String> searchingComboBox;

    // Constructor
    public Page_ContractList(Frame_MainApplication mainFrameApp) {
        super(new BorderLayout());
        this.mainFrameApp = mainFrameApp;
        this.createContractListPage();
        this.saveCurrentTableData();
        this.createListeners();
    }

    public void createContractListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Tools (Title - Search - Filter)
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 143));
        tools.setBorder(new EmptyBorder(10, 25, 5, 25));

        // Title
        JLabel title = new JLabel("Contracts List");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 36.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(0,0,20,0));
        tools.add(title, BorderLayout.NORTH);

        // Search
        this.searchingComboBox = new JComboBox<String>(new String[] {
                "Contract Id",
                "Room",
                "Identifier",
                "Full Name",
                "Checked Out",
                "Started Date",
                "Ended Date"
        });
        JPanel searchingComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);
        JPanel searchingTextFieldPanel =
                InputComboPanel.generateTextInputPanel("Searching (Allow dd/, /MM/ or yyyy)", this.searchingTextField);
        searchingTextFieldPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.27), 65));

        JPanel searchingContainer = new JPanel(new BorderLayout());
        searchingContainer.add(searchingComboBoxContainer, BorderLayout.WEST);
        searchingContainer.add(searchingTextFieldPanel, BorderLayout.EAST);
        tools.add(searchingContainer, BorderLayout.WEST);

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

        // Prepare Date to generate Table.
        String[] columns = {"Contract Id", "Room", "Identifier", "Full Name", "Checked Out", "Started Date",
                "Ended Date", "Detail", "Delete Button"};
        String[][] contracts = Controller_Contract.getAllContractByYearWithTableFormat("0");

        // Create Table
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(contracts, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.table.setRowSorter(new TableRowSorter<>(defaultModel));
        JScrollPane contractScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        contractScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(5);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(65);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(25);

        // Add ScrollPane into CentralPanel/Contract.
        add(tools, BorderLayout.NORTH);
        add(contractScrollPane, BorderLayout.CENTER);
    }

    public void createListeners() {
        // Add Clicking Buttons Action.
        table.addMouseListener(ContractListListeners.getCellActionByMouseListener(mainFrameApp, this,
                this.defaultModel, this.table));

        // Searching Action
        this.searchingTextField.addKeyListener(ContractListListeners.searchTableToGetObjects(this));

        // Get Contracts By Year
        this.filterComboBox.addItemListener(ContractListListeners.getObjectsByYear(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col).toString();
    }

    // Getters
    public Object[][] getTableData() { return this.tableData; }
    public JComboBox<String> getFilterComboBox() { return filterComboBox; }
    public DefaultTableModel getDefaultModel() { return defaultModel; }
    public JTextField getSearchingTextField() { return searchingTextField; }
    public JComboBox<String> getSearchingComboBox() { return searchingComboBox; }
    public JTable getTable() { return table; }
}

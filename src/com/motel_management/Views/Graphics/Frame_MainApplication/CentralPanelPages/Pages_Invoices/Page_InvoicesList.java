package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Graphics.GeneralComponents.TableAsList;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Invoices.InvoicesListListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class Page_InvoicesList extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    private JTable table;
    private JScrollPane invoiceScrollPane;
    private DefaultTableModel defaultModel;
    private Object[][] tableData;

    private JTextField searchingTextField = new JTextField();
    private JComboBox<String> searchingComboBox;

    // Constructor
    public Page_InvoicesList(Frame_MainApplication mainFrameApp) {
        super(new BorderLayout());
        this.mainFrameApp = mainFrameApp;
        this.createInvoicesListPage();
        this.saveCurrentTableData();
        this.createListeners();
    }

    public void createInvoicesListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Tools
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 85));
        tools.setBorder(new EmptyBorder(15, 15, 5, 15));

        // Search
        JPanel searchingContainer = new JPanel(new BorderLayout());
        JPanel searchingTextFieldPanel =
                InputComboPanel.generateTextInputPanel("Searching", this.searchingTextField);

        this.searchingComboBox = new JComboBox<String>(new String[] {
                "Room Id",
                "Last Invoice",
                "Month Payment",
                "Year Payment",
                "Date Created",
                "Total-VNĐ",
                "Was Paid"
        });
        JPanel searchingComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);

        searchingContainer.add(searchingTextFieldPanel, BorderLayout.WEST);
        searchingContainer.add(searchingComboBoxContainer, BorderLayout.EAST);
        tools.add(searchingContainer, BorderLayout.EAST);

        // Title
        JPanel title = new JPanel();
        JLabel titleLabel = new JLabel("Most Recent Invoices");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 34.0f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(15, 0, 0, 0));
        title.add(titleLabel);
        tools.add(title, BorderLayout.WEST);

        // Prepare Data to generate Table.
        ArrayList<String[]> result = Controller_Invoices.getAllLastInvoicesOfRoomWithTableFormat();
        String[][] invoices = new String[result.size()][9];
        for (int i = 0; i < result.size(); i++)
            System.arraycopy(result.get(i), 0, invoices[i], 0, 9);
        String[] columns = { "Room Id", "Invoices Detail", "Last Invoice", "Month Payment", "Year Payment",
                "Date Created", "Total", "Was Paid", "Delete Button"};

        // Generate Table.
        // Make All Value Unchangeable.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(invoices, columns) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 3, 4 -> Integer.class;
                    default -> String.class;
                };
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.table.setRowSorter(new TableRowSorter<>(defaultModel));
        this.invoiceScrollPane = tableAsList.getScrollPane();

        this.table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String)table.getValueAt(row, column);
                this.setHorizontalAlignment(JLabel.CENTER);
                if ("YES".equals(status))   c.setBackground(new Color(184, 207, 229));
                else    c.setBackground(new Color(229, 184, 184));
                return c;
            }
        });

        // Margin Table.
        this.invoiceScrollPane.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(82);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(63);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(63);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(8).setPreferredWidth(73);

        // Add ScrollPane into CentralPanel/Invoices.
        add(tools, BorderLayout.NORTH);
        add(invoiceScrollPane, BorderLayout.CENTER);
    }

    public void createListeners() {
        // Add Clicking View, Update Button Action.
        table.addMouseListener(
                InvoicesListListeners.getAllInvoicesByMouseListener(this.defaultModel, this.table, this, mainFrameApp)
        );

        // Searching
        this.searchingTextField.addKeyListener(InvoicesListListeners.searchTableToGetObjects(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col);
    }

    public Frame_MainApplication getMainFrameApp() {
        return mainFrameApp;
    }
    public JTable getTable() { return table; }
    public JScrollPane getInvoiceScrollPane() { return invoiceScrollPane; }
    public DefaultTableModel getDefaultModel() { return defaultModel; }
    public Object[][] getTableData() { return tableData; }
    public JTextField getSearchingTextField() { return searchingTextField; }
    public JComboBox<String> getSearchingComboBox() { return searchingComboBox; }
}

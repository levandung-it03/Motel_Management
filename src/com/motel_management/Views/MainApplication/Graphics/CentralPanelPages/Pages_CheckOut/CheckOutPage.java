package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_CheckOut;

import com.motel_management.Controllers.Controller_Checkout;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_CheckOut.CheckOutListListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDateTime;

public class CheckOutPage extends JPanel {
    public JTable table;
    public JScrollPane checkOutScrollPane;
    public DefaultTableModel defaultModel;

    public JComboBox<String> filterComboBox;
    public JTextField searchingTextField = new JTextField();
    public JComboBox<String> searchingComboBox;

    public Object[][] tableData;

    public CheckOutPage() {
        super(new BorderLayout());
        this.createCheckOutHistoryPanel();
        this.saveCurrentTableData();
        this.createListeners();
    }
    public void createCheckOutHistoryPanel() {
        JLabel title = new JLabel("Check-Out History");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        add(title, BorderLayout.NORTH);

        // Tools
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 95));
        tools.setBorder(new EmptyBorder(30, 15, 0, 15));

        // Filter
        int currentYear = LocalDateTime.now().getYear();
        this.filterComboBox = new JComboBox<String>(new String[] {
                Integer.toString(currentYear),
                Integer.toString(currentYear - 1),
                Integer.toString(currentYear - 2),
                Integer.toString(currentYear - 3),
                Integer.toString(currentYear - 4),
        });
        JPanel filterComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Filter With Check-out Year", this.filterComboBox);
        tools.add(filterComboBoxContainer, BorderLayout.EAST);

        // Search
        this.searchingComboBox = new JComboBox<String>(new String[] {
                "Check-out ID",
                "Contract ID",
                "Check-out Date"
        });
        JPanel searchingComboBoxContainer = InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);

        JPanel searchingContainer = new JPanel(new BorderLayout());
        JPanel searchingTextFieldPanel = InputComboPanel.generateTextInputPanel("Searching", this.searchingTextField);

        searchingContainer.add(searchingComboBoxContainer, BorderLayout.WEST);
        searchingContainer.add(searchingTextFieldPanel, BorderLayout.EAST);
        tools.add(searchingContainer, BorderLayout.WEST);

        add(tools, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        Object[][] checkout =
                Controller_Checkout.getAllCheckOutByYearWithTableFormat(Integer.toString(LocalDateTime.now().getYear()));
        String[] columns = {"Check-out ID", "Contract ID", "Check-out Date", "Reason"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(checkout, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.table.setRowSorter(new TableRowSorter<>(defaultModel));
        this.checkOutScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.checkOutScrollPane.setBorder(new EmptyBorder(20, 50, 0, 50));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(3).setPreferredWidth(400);

        add(tools, BorderLayout.NORTH);
        add(checkOutScrollPane, BorderLayout.CENTER);
    }

    public void createListeners() {
        // Searching Action
        this.searchingTextField.addKeyListener(CheckOutListListener.searchTableToGetObjects(this));

        // Get CheckOut By Year
        this.filterComboBox.addItemListener(CheckOutListListener.getObjectsByYear(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col).toString();
    }
}

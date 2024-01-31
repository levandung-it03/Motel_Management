package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Graphics.GeneralComponents.TableAsList;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room.RoomPriceHistoryListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDateTime;

public class Page_RoomPriceHistory extends JPanel {
    private JTable table;
    private DefaultTableModel defaultModel;
    private final JTextField searchingTextField = new JTextField();
    private JComboBox<String> filterComboBox;
    private JComboBox<String> searchingComboBox;

    public Object[][] tableData;

    public Page_RoomPriceHistory() {
        super(new BorderLayout());
        this.createRoomPriceHistoryPanel();
        this.saveCurrentTableData();
        this.createListeners();
    }

    public void createRoomPriceHistoryPanel() {
        // Tools (Title - Search - Filter)
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 143));
        tools.setBorder(new EmptyBorder(10, 25, 5, 25));

        // Title
        JLabel title = new JLabel("Room Price History");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 36.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));
        tools.add(title, BorderLayout.NORTH);

        // Search
        this.searchingComboBox = new JComboBox<String>(new String[]{
                "Room ID",
                "Price",
                "Date Created"
        });
        JPanel searchingComboBoxContainer = InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);
        searchingComboBoxContainer.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth * 0.17), 75));

        JPanel searchingContainer = new JPanel(new BorderLayout());
        JPanel searchingTextFieldPanel = InputComboPanel.generateTextInputPanel("Searching", this.searchingTextField);
        searchingTextFieldPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth * 0.16), 75));
        searchingContainer.add(searchingComboBoxContainer, BorderLayout.WEST);
        searchingContainer.add(searchingTextFieldPanel, BorderLayout.EAST);

        tools.add(searchingContainer, BorderLayout.WEST);

        // Filter
        int currentYear = LocalDateTime.now().getYear();
        this.filterComboBox = new JComboBox<String>(new String[]{
                Integer.toString(currentYear),
                Integer.toString(currentYear - 1),
                Integer.toString(currentYear - 2),
                Integer.toString(currentYear - 3),
                Integer.toString(currentYear - 4),
        });
        JPanel filterComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Filter With Checkout Year", this.filterComboBox);
        tools.add(filterComboBoxContainer, BorderLayout.EAST);

        // Prepare Data to generate Table.
        Object[][] data =
                Controller_Room.getAllRoomPriceHistoryByYearWithTableFormat(Integer.toString(LocalDateTime.now().getYear()));
        String[] columns = {"Room ID", "Price", "Date Created"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.table.setRowSorter(new TableRowSorter<>(defaultModel));
        JScrollPane checkOutScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        checkOutScrollPane.setBorder(new EmptyBorder(20, 25, 0, 25));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(300);

        add(tools, BorderLayout.NORTH);
        add(checkOutScrollPane, BorderLayout.CENTER);
    }

    public void createListeners() {
        // Searching Action
        this.searchingTextField.addKeyListener(RoomPriceHistoryListeners.searchTableToGetObjects(this));

        // Get CheckOut By Year
        this.filterComboBox.addItemListener(RoomPriceHistoryListeners.getObjectsByYear(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col).toString();
    }

    // Getters
    public DefaultTableModel getDefaultModel() {
        return defaultModel;
    }

    public JComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }

    public JComboBox<String> getSearchingComboBox() {
        return searchingComboBox;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getSearchingTextField() {
        return searchingTextField;
    }

    public Object[][] getTableData() {
        return tableData;
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract.ContractListListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Representatives.RepresentativesListeners;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;

public class RepresentativesListPage extends JPanel {
    JFrame mainAppFrame;
    public JTable table;
    public JScrollPane representativesScrollPane;
    public DefaultTableModel defaultModel;

    public JComboBox<String> filterComboBox;
    public JTextField searchingTextField = new JTextField();
    public JComboBox<String> searchingComboBox;

    public Object[][] tableData;

    public RepresentativesListPage(JFrame mainAppFrame) {
        super(new BorderLayout());
        this.mainAppFrame = mainAppFrame;
        this.createRepresentativesListPage();
        this.saveCurrentTableData();
        this.createListener();
    }

    public void createRepresentativesListPage() {
        // Tools
        JPanel tools = new JPanel(new BorderLayout());
        tools.setPreferredSize(new Dimension(Configs.centralPanelWidth, 145));
        tools.setBorder(new EmptyBorder(10, 25, 5, 25));

        // Title
        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Representatives");
        title.setBorder(new EmptyBorder(0,0,10,0));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 36.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(title,BorderLayout.CENTER);
        tools.add(headerPanel, BorderLayout.NORTH);

        // Search
        JPanel searchingContainer = new JPanel(new BorderLayout());
        this.searchingComboBox = new JComboBox<String>(new String[] {
                "Room Id",
                "Identifier",
                "First Name",
                "Last Name",
                "Gender",
                "Job Titles",
                "Phone Number"
        });
        JPanel searchingComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);
        searchingComboBoxContainer.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.17), 75));

        JPanel searchingTextFieldPanel =
                InputComboPanel.generateTextInputPanel("Searching", this.searchingTextField);
        searchingTextFieldPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.16), 75));

        searchingContainer.add(searchingTextFieldPanel, BorderLayout.WEST);
        searchingContainer.add(searchingComboBoxContainer, BorderLayout.EAST);
        tools.add(searchingContainer, BorderLayout.WEST);

        // Filter
        int currentYear = LocalDateTime.now().getYear();
        this.filterComboBox = new JComboBox<String>(new String[] {
                "Now Is Occupying",
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
        String[][] representatives = Controller_Representatives.getAllRepresentativesWithTableFormat("0");
        String[] columns = {"Room Id", "Identifier", "First Name", "Last Name", "Gender", "Job Titles", "Phone Number", "Details"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(representatives, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultModel = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.representativesScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.representativesScrollPane.setBorder(new EmptyBorder(20, 30, 0, 30));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(70);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(30);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(15);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(40);

        // Add ScrollPane into CentralPanel/Room.
        this.add(tools, BorderLayout.NORTH);
        this.add(representativesScrollPane, BorderLayout.CENTER);
    }

    public void createListener(){
        // View Detail Action
        table.addMouseListener(RepresentativesListeners.getInformationByClick(mainAppFrame, table));

        // Searching Action
        this.searchingTextField.addKeyListener(RepresentativesListeners.searchTableToGetObjects(this));

        // Get Representatives By Year
        this.filterComboBox.addItemListener(RepresentativesListeners.getObjectsByYear(this));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        tableData = new Object[this.table.getRowCount()][this.table.getColumnCount()];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount(); col++)
                tableData[row][col] = this.table.getValueAt(row, col).toString();
    }
}

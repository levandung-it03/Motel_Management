package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Representatives.RepresentativesListeners;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RepresentativesListPage extends JPanel {
    public JTable table;
    public JScrollPane representativesScrollPane;
    public DefaultTableModel defaultTable;
    private JPanel headerPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JButton searchButton;
    private JTextField searchField = new JTextField(4);

    public RepresentativesListPage() {
        super(new BorderLayout());
        this.createRepresentativesHeaderPanel();
        this.createSearchingPanel();
        this.createRepresentativesListPage();
        this.createListener();
    }

    public void createRepresentativesHeaderPanel(){
        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        headerPanel.setPreferredSize(new Dimension(Configs.centralPanelWidth, 160));
        headerPanel.setLayout(new BorderLayout());
//        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        JLabel title = new JLabel("Representatives List");
        title.setBorder(new EmptyBorder(20,0,10,0));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 36.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(title,BorderLayout.CENTER);
    }

    public void createSearchingPanel(){
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));

        this.searchButton = InputComboPanel.generateButton("Search");

        searchPanel.add(createInputPanel("Search By Year",this.searchField));
        searchPanel.add(searchButton);
        headerPanel.add(searchPanel,BorderLayout.SOUTH);
        this.add(headerPanel,BorderLayout.NORTH);
    }

    public static JPanel createInputPanel(String strLabel, JTextField originInp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 15, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.12), 65));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }



    public void createRepresentativesListPage() {
        // Prepare Date to generate Table.
        String[][] representatives = Controller_Representatives.getAllRepresentativesWithTableFormat();
        String[] columns = {"Identifier", "First name", "Room Id", "Phone number", "Permanent Address" ,"Details Information"};


        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(representatives, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultTable = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.representativesScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.representativesScrollPane.setBorder(new EmptyBorder(20, 10, 0, 10));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(80);


        // Add ScrollPane into CentralPanel/Room.
        this.add(representativesScrollPane);
    }

    public void createListener(){
        table.addMouseListener(RepresentativesListeners.getInformationByClick(table));
//        searchButton.addActionListener(RepresentativesListeners);
    }



}

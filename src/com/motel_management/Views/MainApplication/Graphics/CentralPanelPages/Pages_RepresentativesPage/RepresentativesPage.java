package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Representatives.RepresentativesListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RepresentativesPage extends JPanel{
    private JTable table = new JTable();
    private JScrollPane representativesScrollPane = new JScrollPane();
    public DefaultTableModel defaultTable = new DefaultTableModel();

    public RepresentativesPage() {
        super(new BorderLayout());
        this.createPresentativesPanel();
        this.createPresentativesListPage();
        this.createListener();
    }

    public void createPresentativesPanel(){
        JLabel title = new JLabel("Representatives List");
        title.setBorder(new EmptyBorder(20,0,10,0));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title,BorderLayout.NORTH);
    }
    public void createPresentativesListPage() {
        this.setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

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
        this.add(representativesScrollPane,BorderLayout.CENTER);
    }

    public void createListener(){
        table.addMouseListener(RepresentativesListeners.getInformationByClick(table));
    }


}
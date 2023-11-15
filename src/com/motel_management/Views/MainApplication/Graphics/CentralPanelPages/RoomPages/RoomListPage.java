package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Views.Configs;
import com.motel_management.Controllers.Controller_RoomList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners.RoomListListeners;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class RoomListPage extends JPanel {
    JTable roomTable;
    JScrollPane roomScrollPane;
    DefaultTableModel defaultRoomTable;

    // Constructor
    public RoomListPage() {
        super(new GridLayout());
        this.createRoomListPage();
        this.createListeners();
    }

    public void createRoomListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        String[][] rooms = Controller_RoomList.getAllRoom();
        String[] columns = {"Room Code", "Number of People", "Maximum Quantity", "Default Room Price", "Delete Button"};

        this.defaultRoomTable = new DefaultTableModel(rooms, columns);
        this.defaultRoomTable.fireTableDataChanged();

        this.roomTable = new JTable(defaultRoomTable);
        JTableHeader header = roomTable.getTableHeader();
        header.setFont(Configs.labelFont);
        this.roomTable.setFont(Configs.labelFont);
        this.roomTable.setRowHeight(30);

        this.roomTable.getColumnModel().getColumn(this.roomTable.getColumnCount() - 1).setCellRenderer(
            new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setHorizontalAlignment(JLabel.CENTER);
                    setBackground(Color.RED);
                    return this;
                }
            }
        );

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setVerticalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.roomTable.getColumnCount() - 1; i++)
            this.roomTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        this.roomTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        this.roomTable.getColumnModel().getColumn(4).setPreferredWidth(40);

        this.roomScrollPane = new JScrollPane(roomTable);
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth/3));
        this.roomTable.setBorder(new LineBorder(Configs.blackTextColor, 1, true));
        add(roomScrollPane);
    }

    public void createListeners() {

    }
}

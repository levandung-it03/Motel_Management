package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.Controllers.Controller_RoomList;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners.RoomListListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RoomListPage extends JPanel {
    JTable roomTable;
    JScrollPane roomScrollPane;

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

        DefaultTableModel defaultRoomTable = new DefaultTableModel(rooms, columns);
        this.roomTable = new JTable(defaultRoomTable);
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
        for (int i = 0; i < this.roomTable.getColumnCount() - 1; i++)
            this.roomTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);


        this.roomScrollPane = new JScrollPane(roomTable);
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth*4/9));
        this.roomTable.setBorder(new LineBorder(Configs.blackTextColor, 1, true));
        add(roomScrollPane);
    }

    public void createListeners() {

    }
}

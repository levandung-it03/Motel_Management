package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.Configs;
import com.motel_management.Controllers.Controller_RoomList;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.TableAsList;
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

        // Prepare Date to generate Table.
        String[][] rooms = Controller_RoomList.getAllRoom();
        String[] columns = {"Room Code", "Number of People", "Maximum Quantity", "Default Room Price", "Delete Button"};

        TableAsList tableAsList = new TableAsList(rooms, columns);
        this.defaultRoomTable = tableAsList.getDefaultModel();
        this.roomTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth/3));

        // Resize several Columns.
        this.roomTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        this.roomTable.getColumnModel().getColumn(4).setPreferredWidth(40);

        // Add ScrollPane into CentralPanel/Room.
        add(roomScrollPane);
    }

    public void createListeners() {
        roomTable.getModel().addTableModelListener(RoomListListeners.cellValueUpdated(roomTable));
    }
}

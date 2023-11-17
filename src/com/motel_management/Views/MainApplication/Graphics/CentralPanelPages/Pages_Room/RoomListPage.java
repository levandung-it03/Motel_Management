package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Configs;
import com.motel_management.Controllers.Controller_RoomList;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.RoomListListeners;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class RoomListPage extends JPanel {
    public JTable table;
    public JScrollPane roomScrollPane;
    public DefaultTableModel defaultTable;
    public Object[][] tableData;

    // Constructor
    public RoomListPage() {
        super(new GridLayout());
        this.createRoomListPage();
        this.createListeners();
        this.saveCurrentTableData();
    }

    public void createRoomListPage() {
        setPreferredSize(new Dimension(Configs.centralPanelWidth, Configs.centralPanelHeight));

        // Prepare Date to generate Table.
        String[][] rooms = Controller_RoomList.getAllRoom();
        String[] columns = {"Room Code", "Number of People", "Maximum Quantity", "Default Room Price", "Delete Button"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(rooms, columns);
        this.defaultTable = tableAsList.getDefaultModel();
        this.table = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.roomScrollPane.setBorder(new EmptyBorder(20, 20, 0, Configs.centralPanelWidth/4));

        // Resize several Columns.
        this.table.getColumnModel().getColumn(0).setPreferredWidth(25);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(40);

        // Add ScrollPane into CentralPanel/Room.
        add(roomScrollPane);
    }

    public void createListeners() {
        // Add Changing Cells Value Action.
        table.getModel().addTableModelListener(RoomListListeners.cellValueUpdated(this));

        // Add Clicking Delete Button Action.
        table.addMouseListener(GeneralListeners.getCustomDeleteButtonMouseAdapter(this.defaultTable, this.table));
    }

    public void saveCurrentTableData() {
        // Copy Data from Table.
        this.tableData = new Object[this.table.getRowCount()][this.table.getColumnCount() - 1];
        for (int row = 0; row < this.table.getRowCount(); row++)
            for (int col = 0; col < this.table.getColumnCount() - 1; col++)
                this.tableData[row][col] = this.table.getValueAt(row, col);
    }

}

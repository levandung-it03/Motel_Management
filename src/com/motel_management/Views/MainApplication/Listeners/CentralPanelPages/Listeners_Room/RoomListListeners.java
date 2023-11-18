package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room.RoomListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomListListeners {
    static TableModelListener tmListener;

    // Constructor
    public RoomListListeners() {}

    public static TableModelListener cellValueUpdated(RoomListPage roomList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                if (evt.getType() == TableModelEvent.UPDATE) {
                    String[] changedRow = GeneralListeners.getChangedTableRow(evt, tmListener, roomList.table,
                            roomList.tableData, "Room");

                    if (changedRow != null) {
                        if (Controller_Room.updateRoom(changedRow) != 0)
                            JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                roomList.saveCurrentTableData();
            }
        };
        return tmListener;
    }

    public static MouseAdapter getDeleteCellByMouseListener(DefaultTableModel defaultTable, JTable table, RoomListPage roomList) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // Delete Button Clicked
                if (clickedColumn == table.getColumnCount() - 1) {
                    if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this row?", "Confirm",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        if (Controller_Room.deleteById(table.getValueAt(clickedRow, 0).toString()) != 0) {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                            defaultTable.removeRow(clickedRow);
                            roomList.saveCurrentTableData();
                        } else {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }

            }
        };
    }

}



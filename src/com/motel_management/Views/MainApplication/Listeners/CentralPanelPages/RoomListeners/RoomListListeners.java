package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners;

import com.motel_management.DataAccessObject.RoomDAO;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class RoomListListeners {
    // Constructor
    public RoomListListeners() {}

    public static TableModelListener cellValueUpdated(JTable mainTable) {
        String[] changedRow = new String[mainTable.getColumnCount() - 1];
        return new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int r = e.getFirstRow();
                    DefaultTableModel model = (DefaultTableModel) e.getSource();
                    changedRow[0] = model.getValueAt(r, 0).toString();
                    for (int i = 0; i < changedRow.length; i++)
                        changedRow[i] = model.getValueAt(r, i).toString();
                    RoomDAO.getInstance().update(changedRow);
                }
            }
        };
    }
}

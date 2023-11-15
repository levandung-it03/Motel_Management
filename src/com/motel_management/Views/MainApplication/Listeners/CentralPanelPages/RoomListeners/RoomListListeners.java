package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages.RoomListPage;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class RoomListListeners {
    // Constructor
    public RoomListListeners() {}

    public static TableModelListener cellValueUpdated(JTable mainTable, RoomListPage roomList) {
        String[] changedRow = new String[mainTable.getColumnCount() - 1];
        return new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int rowChanged = e.getFirstRow();
                    int colChanged = e.getColumn();
                    String oldData = roomList.tableData[rowChanged][colChanged].toString();

                    if (JOptionPane.showConfirmDialog(mainTable, "Are you sure want to update this cell?",
                            "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                        DefaultTableModel model = (DefaultTableModel) e.getSource();
                        String changedValue = model.getValueAt(rowChanged, colChanged).toString();
                        changedRow[0] = model.getValueAt(rowChanged, 0).toString();

                        for (int i = 0; i < changedRow.length; i++)
                            changedRow[i] = model.getValueAt(rowChanged, i).toString();

                        if (!Configs.isNumeric(oldData) || Configs.isNumeric(changedValue) &&
                                Integer.parseInt(changedRow[1]) <= Integer.parseInt(changedRow[2])) {
    //                    RoomDAO.getInstance().update(changedRow);
                            System.out.println("updated");
                        } else {
                            JOptionPane.showMessageDialog(mainTable, "Invalid Value", "Confirmation",
                                    JOptionPane.PLAIN_MESSAGE);
//                            mainTable.setValueAt(oldData, rowChanged, colChanged);
                        }
                    } else {
//                        mainTable.setValueAt(oldData, rowChanged, colChanged);
                    }
                }
                roomList.getTableData();
            }
        };
    }
}

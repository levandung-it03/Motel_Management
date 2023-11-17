package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContractListListeners {
    static TableModelListener tmListener;

    // Constructor
    public ContractListListeners() {}

    public static TableModelListener cellValueUpdated(ContractListPage contractList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    String[] res = GeneralListeners.getChangedTableRow(e, tmListener, contractList.table, contractList.tableData);

                    if (res != null) {
                        System.out.println("Value Updated");
                        ContractDAO.getInstance().update(res);
                    }
                }
                contractList.saveCurrentTableData();
            }
        };
        return tmListener;
    }

    public static MouseAdapter getCustomDeleteButtonMouseAdapter(DefaultTableModel defaultTable, JTable table) {
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
                        if (Controller_Contract.deleteById(table.getValueAt(clickedRow, 0).toString()) != 0) {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                            defaultTable.removeRow(clickedRow);
                        } else {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }

            }
        };
    }

}
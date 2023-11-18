package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water;

import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Controllers.Controller_Room;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage.Electricity_WaterListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EWListListeners {
    static TableModelListener tmListener;

    // Constructor
    public EWListListeners() {}

    public static TableModelListener cellValueUpdated(Electricity_WaterListPage EWList,JTable table) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    String[] res = GeneralListeners.getChangedTableRow(e, tmListener, table, EWList.tableData);

                    if (res != null) {
                        System.out.println("Value Updated");
                        RoomDAO.getInstance().update(res);
                    }
                }
                EWList.saveCurrentTableData(table);
            }
        };
        return tmListener;
    }

    public static MouseAdapter getCustomDeleteButtonMouseAdapterOfElectric(DefaultTableModel defaultTable, JTable table) {
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
                        if (Controller_Electricity_Water.deleteElectricById(table.getValueAt(clickedRow, 0).toString()) != 0) {
                            System.out.println(Controller_Electricity_Water.deleteElectricById(table.getValueAt(clickedRow, 0).toString()));
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

    public static MouseAdapter getCustomDeleteButtonMouseAdapterOfWater(DefaultTableModel defaultTable, JTable table) {
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
                        if (Controller_Electricity_Water.deleteWaterById(table.getValueAt(clickedRow, 0).toString()) != 0) {
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

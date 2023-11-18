package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water;

import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Electricity_Water.Electricity_WaterListPage;
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

    public static TableModelListener cellElectricValueUpdated(Electricity_WaterListPage EWList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                if (evt.getType() == TableModelEvent.UPDATE) {
                    String[] changedRow = GeneralListeners.getChangedTableRow(evt, tmListener, EWList.electricTable,
                            EWList.tableData, "Room");

                    if (changedRow != null) {
                        if (Controller_Electricity_Water.updateElectric(changedRow) != 0)
                            JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                EWList.saveCurrentTableData(EWList.electricTable);
            }
        };
        return tmListener;
    }
    public static TableModelListener cellWaterValueUpdated(Electricity_WaterListPage EWList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                if (evt.getType() == TableModelEvent.UPDATE) {
                    String[] changedRow = GeneralListeners.getChangedTableRow(evt, tmListener, EWList.waterTable,
                            EWList.tableData, "Room");

                    if (changedRow != null) {
                        if (Controller_Electricity_Water.updateWater(changedRow) != 0)
                            JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                EWList.saveCurrentTableData(EWList.waterTable);
            }
        };
        return tmListener;
    }

    public static MouseAdapter getDeleteCellByMouseListenerOfElectric(DefaultTableModel defaultModel, JTable table) {
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
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                            defaultModel.removeRow(clickedRow);
                        } else {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }

            }
        };
    }

    public static MouseAdapter getDeleteCellByMouseListenerOfWater(DefaultTableModel defaultModel, JTable table) {
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
                            defaultModel.removeRow(clickedRow);
                        } else {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }

            }
        };
    }
}

package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Electricity_Water;

import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.GeneralCentralPanelListeners;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Electricity_Water.Page_ElectricityWaterList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EWListListeners {
    static TableModelListener tmElectricListener;
    static TableModelListener tmWaterListener;

    // Constructor
    public EWListListeners() {
    }

    public static TableModelListener cellElectricValueUpdated(Page_ElectricityWaterList EWList) {
        tmElectricListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                if (evt.getType() == TableModelEvent.UPDATE) {
                    String[] changedRow = GeneralCentralPanelListeners.getChangedTableRow(evt, tmElectricListener, EWList.electricTable,
                            EWList.electricTableData);

                    if (changedRow != null) {
                        if (Controller_Electricity_Water.updateElectric(changedRow) != 0)
                            JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
        return tmElectricListener;
    }

    public static TableModelListener cellWaterValueUpdated(Page_ElectricityWaterList EWList) {
        tmWaterListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                if (evt.getType() == TableModelEvent.UPDATE) {
                    String[] changedRow = GeneralCentralPanelListeners.getChangedTableRow(evt, tmWaterListener, EWList.waterTable,
                            EWList.waterTableData);

                    if (changedRow != null) {
                        if (Controller_Electricity_Water.updateWater(changedRow) != 0)
                            JOptionPane.showMessageDialog(new JPanel(), "Update Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(new JPanel(), "Update Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
        return tmWaterListener;
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
                    if (clickedRow == table.getRowCount() - 1) {
                        if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this row?", "Confirm",
                                JOptionPane.YES_NO_OPTION) == 0) {
                            if (Controller_Electricity_Water.deleteElectricById(table.getValueAt(clickedRow, 0).toString()) != 0) {
                                JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                                defaultModel.removeRow(clickedRow);
                            } else {
                                JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                            }
                        }
                    }else {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
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
                    if (clickedRow == table.getRowCount() - 1) {
                        if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this row?", "Confirm",
                                JOptionPane.YES_NO_OPTION) == 0) {
                            if (Controller_Electricity_Water.deleteWaterById(table.getValueAt(clickedRow, 0).toString()) != 0) {
                                JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                                defaultModel.removeRow(clickedRow);
                            } else {
                                JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                            }
                        }
                    }else {
                        JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                    }
                }

            }
        };
    }
}

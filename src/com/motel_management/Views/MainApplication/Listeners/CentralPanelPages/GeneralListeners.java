package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class GeneralListeners {
    public GeneralListeners() { }

    public static String[] getChangedTableRow(TableModelEvent e, TableModelListener listener, JTable table,
                                              Object[][] oldData, String tableName) {
        int changedRowIndex = e.getFirstRow();
        int changedColumnIndex = e.getColumn();
        Object oldCellData = oldData[changedRowIndex][changedColumnIndex];

        if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm this action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            String[] fullChangedRow = new String[table.getColumnCount() - 1];

            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String changedValue = model.getValueAt(changedRowIndex, changedColumnIndex).toString();

            for (int i = 0; i < fullChangedRow.length; i++)
                fullChangedRow[i] = model.getValueAt(changedRowIndex, i).toString();

            boolean isValid = false;

            if (tableName.equals("Room")) {
                isValid = GeneralListeners.validateRoomTableData(oldCellData, changedValue, fullChangedRow);
            }

            if (isValid) {
                return fullChangedRow;
            } else {
                JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);

                // Paying back Old Data.
                table.getModel().removeTableModelListener(listener);
                table.setValueAt(oldCellData, changedRowIndex, changedColumnIndex);
                table.getModel().addTableModelListener(listener);
            }
        }
        else {
            // Paying back Old Data.
            table.getModel().removeTableModelListener(listener);
            table.setValueAt(oldCellData, changedRowIndex, changedColumnIndex);
            table.getModel().addTableModelListener(listener);
        }
        return null;
    }

    public static boolean validateRoomTableData(Object oldCellData, String changedValue, String[] fullChangedRow) {
        return (!Configs.isIntegerNumeric(oldCellData.toString()) || Configs.isIntegerNumeric(changedValue))
                && Integer.parseInt(fullChangedRow[1]) <= Integer.parseInt(fullChangedRow[2])
                && Integer.parseInt(fullChangedRow[1]) >= 0
                && Integer.parseInt(fullChangedRow[3]) >= 0;
    }

}

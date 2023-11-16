package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GeneralListeners {
    public GeneralListeners() { }

    public static String[] getChangedTableRow(TableModelEvent e, TableModelListener listener, JTable table, Object[][] oldData) {
        int changedRowIndex = e.getFirstRow();
        int changedColumnIndex = e.getColumn();
        Object oldCellData = oldData[changedRowIndex][changedColumnIndex];

        if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm this action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            String[] fullChangedRow = new String[table.getColumnCount() - 1];

            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String changedValue = model.getValueAt(changedRowIndex, changedColumnIndex).toString();

            for (int i = 0; i < fullChangedRow.length; i++)
                fullChangedRow[i] = model.getValueAt(changedRowIndex, i).toString();

            boolean isValid =
                    (!Configs.isIntegerNumeric(oldCellData.toString()) || Configs.isIntegerNumeric(changedValue))
                            && Integer.parseInt(fullChangedRow[1]) <= Integer.parseInt(fullChangedRow[2])
                            && Integer.parseInt(fullChangedRow[1]) >= 0;

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

    public static MouseAdapter getCustomDeleteButtonMouseAdapter(DefaultTableModel model, JTable table) {
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

                        if (RoomDAO.getInstance().delete(table.getValueAt(clickedRow, 0).toString()) != 0)
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);

                        model.removeRow(clickedRow);
                    }
                }
            }
        };
    }
}

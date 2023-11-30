package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ContractListListeners {
    static TableModelListener tmListener;

    // Constructor
    public ContractListListeners() {}

    public static MouseAdapter getDeleteCellByMouseListener(DefaultTableModel defaultModel, JTable table, ContractListPage contractList) {
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
                        if (Controller_Contract.deleteById(
                                table.getValueAt(clickedRow, 0).toString(),
                                table.getValueAt(clickedRow, 1).toString()
                        ) != 0) {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);
                            defaultModel.removeRow(clickedRow);
                            contractList.saveCurrentTableData();
                        } else {
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Failed!", "Notice", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }

            }
        };
    }

    public static KeyListener searchTableToGetObjects(ContractListPage page) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // Make page.tableData Update Continuous.
                GeneralListeners.searchTableToGetObjects(page.searchingTextField, page.searchingComboBox, page.table,
                        page.tableData, page.defaultModel);
            }
        };
    }

    public static ItemListener getObjectsByYear(ContractListPage page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get Year Input (default =0 with Specified Condition).
                String selectedYear = Objects.requireNonNull(page.filterComboBox.getSelectedItem()).toString();
                try { Integer.parseInt(selectedYear);}
                catch (NumberFormatException exc) { selectedYear = "0"; }

                // Call API To Get Table Data.
                Object[][] result = Controller_Contract.getAllContractByYearWithTableFormat(selectedYear);

                // Clear Current Table Data.
                page.defaultModel.setRowCount(0);

                // Add Row By Row result[][] Into Default Table Model.
                for (Object[] row : result)
                    page.defaultModel.addRow(row);

                // Notice To Application That There Are Changes In Our Table.
                page.defaultModel.fireTableDataChanged();

                // Save Current Data For Updating, Searching,...
                page.saveCurrentTableData();

                return;
            }
        };
    }
}

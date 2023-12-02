package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract.Dialog_DetailContract;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract.Page_ContractList;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.GeneralCentralPanelListeners;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ContractListListeners {
    // Constructor
    public ContractListListeners() { super(); }

    public static MouseAdapter getCellActionByMouseListener(Frame_MainApplication mainFrameApp,
                                                            Page_ContractList contractList,
                                                            DefaultTableModel defaultModel,
                                                            JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // View Button Clicked
                if (clickedColumn == 7) {
                    ContractModel selectedContract = Controller_Contract.getContractById(
                            table.getValueAt(clickedRow, 0).toString()
                    );
                    Dialog_DetailContract detailContract = new Dialog_DetailContract(
                            mainFrameApp,
                            selectedContract,
                            table.getValueAt(clickedRow, 3).toString()
                    );
                }

                // Delete Button Clicked
                if (clickedColumn == 8) {
                    if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this row?", "Confirm",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        if (Controller_Contract.deleteById(
                                table.getValueAt(clickedRow, 0).toString(),
                                table.getValueAt(clickedRow, 1).toString(),
                                table.getValueAt(clickedRow, 2).toString()
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

    public static KeyListener searchTableToGetObjects(Page_ContractList page) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // Make page.tableData Update Continuous.
                GeneralCentralPanelListeners.searchTableToGetObjects(
                        page.getSearchingTextField(),
                        page.getSearchingComboBox(),
                        page.getTable(),
                        page.getTableData(),
                        page.getDefaultModel()
                );
            }
        };
    }

    public static ItemListener getObjectsByYear(Page_ContractList page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get Year Input (default =0 with Specified Condition).
                String selectedYear = Objects.requireNonNull(page.getFilterComboBox().getSelectedItem()).toString();
                try { Integer.parseInt(selectedYear);}
                catch (NumberFormatException exc) { selectedYear = "0"; }

                // Call API To Get Table Data.
                Object[][] result = Controller_Contract.getAllContractByYearWithTableFormat(selectedYear);

                // Clear Current Table Data.
                page.getDefaultModel().setRowCount(0);

                // Add Row By Row result[][] Into Default Table Model.
                for (Object[] row : result)
                    page.getDefaultModel().addRow(row);

                // Notice To Application That There Are Changes In Our Table.
                page.getDefaultModel().fireTableDataChanged();

                // Save Current Data For Updating, Searching,...
                page.saveCurrentTableData();

                return;
            }
        };
    }
}

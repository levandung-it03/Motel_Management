package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Contract;

import com.motel_management.Controllers.Controller_Contract;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract.ContractListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

    public static KeyListener searchContract(ContractListPage page) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                String textValue = page.searchingTextField.getText();
                String selectedField = Objects.requireNonNull(page.searchingComboBox.getSelectedItem()).toString();
                int columnInd = page.table.getColumn(selectedField).getModelIndex();
                System.out.println(textValue);
                page.defaultModel.setRowCount(0);
                if (textValue.trim().isEmpty()) {
                    for (Object[] objects : page.tableData)
                        page.defaultModel.addRow(objects);
                }
                else {
                    ArrayList<Object[]> arrListRes = new ArrayList<>();
                    for (int row = 0; row < page.tableData.length; row++) {
                        if (page.tableData[row][columnInd].toString().contains(textValue)) {
                            System.out.println(page.tableData[row][0]);
                            arrListRes.add(page.tableData[row]);
                        }
                    }
                    Object[][] result = new Object[arrListRes.size()][8];
                    for (int ind = 0; ind < arrListRes.size(); ind++)
                        result[ind] = arrListRes.get(ind);

                    for (Object[] objects : result)
                        page.defaultModel.addRow(objects);
                }
                page.defaultModel.fireTableDataChanged();
            }
        };
    }

    public static ActionListener getContractsByYear(ContractListPage page) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = Objects.requireNonNull(page.filterComboBox.getSelectedItem()).toString();
                if (selectedYear.contains("N"))     selectedYear = "0";

                String[][] result = Controller_Contract.getAllContractByYearWithTableFormat(selectedYear);

                page.defaultModel.setRowCount(0);
                for (String[] row : result)
                    page.defaultModel.addRow(row);
                page.defaultModel.fireTableDataChanged();
                page.saveCurrentTableData();
            }
        };
    }
}



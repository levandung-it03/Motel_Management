package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Representatives;

import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.RepresentativesListPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.Representatives_ShowID;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class RepresentativesListeners {
    public static MouseAdapter getInformationByClick(JFrame mainAppFrame, JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // View Button Clicked
                if (clickedColumn == table.getColumnCount() - 1) {
                    PersonModel res =
                            Controller_Representatives.getPersonById(String.valueOf(table.getValueAt(clickedRow,1)));
                    new Representatives_ShowID(mainAppFrame, res);
                }
            }
        };
    }

    public static KeyListener searchTableToGetObjects(RepresentativesListPage page) {
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

    public static ItemListener getObjectsByYear(RepresentativesListPage page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get Year Input (default =0 with Specified Condition).
                String selectedYear = Objects.requireNonNull(page.filterComboBox.getSelectedItem()).toString();
                try { Integer.parseInt(selectedYear);}
                catch (NumberFormatException exc) { selectedYear = "0"; }

                // Call API To Get Table Data.
                Object[][] result = Controller_Representatives.getAllRepresentativesWithTableFormat(selectedYear);

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
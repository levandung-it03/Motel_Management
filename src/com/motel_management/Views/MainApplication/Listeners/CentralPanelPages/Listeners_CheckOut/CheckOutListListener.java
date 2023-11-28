package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_CheckOut;

import com.motel_management.Controllers.Controller_Checkout;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_CheckOut.CheckOutPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import java.awt.event.*;
import java.util.Objects;

public class CheckOutListListener {
    public CheckOutListListener() { super(); }

    public static KeyListener searchTableToGetObjects(CheckOutPage page) {
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

    public static ItemListener getObjectsByYear(CheckOutPage page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get Year Input (default =0 with Specified Condition).
                String selectedYear = Objects.requireNonNull(page.filterComboBox.getSelectedItem()).toString();
                try { Integer.parseInt(selectedYear);}
                catch (NumberFormatException exc) { selectedYear = "0"; }

                // Call API To Get Table Data.
                Object[][] result = Controller_Checkout.getAllCheckOutByYearWithTableFormat(selectedYear);

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

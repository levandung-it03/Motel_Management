package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesListPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesOfRoomDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class InvoicesListListeners {
    public InvoicesListListeners() { super(); }

    public static MouseAdapter getMouseListener(DefaultTableModel defaultModel, JTable table, InvoicesListPage invoicesList,
                                                JFrame mainFrameApp) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // Update Button Clicked
                if (clickedColumn == 8)
                    InvoicesListListeners.updatePaymentStatus(clickedRow, table);

                // View Button Clicked
                if (clickedColumn == 1)
                    InvoicesListListeners.viewAllInvoice(table.getValueAt(clickedRow, 0).toString(), mainFrameApp);
            }
        };
    }

    public static void viewAllInvoice(String roomId, JFrame mainFrameApp) {
        InvoicesOfRoomDialog invoicesOfRoom = new InvoicesOfRoomDialog(mainFrameApp, roomId);
    }

    public static void updatePaymentStatus(int clickedRow, JTable table) {
        if (JOptionPane.showConfirmDialog(new Panel(), "Confirm Changing Invoice Payment Status?",
        "Confirm", JOptionPane.YES_NO_OPTION) == 0) {

            HashMap<String, String> result =
                    Controller_Invoices.updateInvoiceStatus(table.getValueAt(clickedRow, 2).toString());
            JOptionPane.showConfirmDialog(new Panel(), result.get("message"), "Notice", JOptionPane.DEFAULT_OPTION);
            if (result.get("result").equals("1"))
                table.setValueAt(table.getValueAt(clickedRow, 7) == "NO" ? "YES" : "NO", clickedRow, 7);

        }
    }
}

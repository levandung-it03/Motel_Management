package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesListPage;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoicesOfRoomDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class InvoicesListListeners {
    public InvoicesListListeners() { super(); }

    public static MouseAdapter getAllInvoicesByMouseListener(DefaultTableModel defaultModel, JTable table,
                                                             InvoicesListPage invoicesList, JFrame mainFrameApp) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // Delete Button Clicked
                if (clickedColumn == 8)
                    InvoicesListListeners.deleteInvoice(clickedRow, invoicesList, defaultModel);

                // View Button Clicked
                if (clickedColumn == 1)
                    InvoicesListListeners.viewAllInvoice(table.getValueAt(clickedRow, 0).toString(), mainFrameApp, invoicesList);
            }
        };
    }

    public static void viewAllInvoice(String roomId, JFrame mainFrameApp, InvoicesListPage invoicesList) {
        InvoicesOfRoomDialog invoicesOfRoom = new InvoicesOfRoomDialog(mainFrameApp, roomId, invoicesList);
    }

    public static void deleteInvoice(int clickedRow, InvoicesListPage invoicesList, DefaultTableModel defaultModel) {
        if (JOptionPane.showConfirmDialog(new JPanel(), "Do you want to delete Invoice "
                + invoicesList.tableData[clickedRow][2] + "?", "Notice", JOptionPane.YES_NO_OPTION) == 0) {
            if (Controller_Invoices.deleteInvoice(invoicesList.tableData[clickedRow][2].toString()) != 0) {
                JOptionPane.showMessageDialog(new JPanel(), "Delete Invoice " + invoicesList.tableData[clickedRow][2]
                        + " Successfully!", "Notice", JOptionPane.PLAIN_MESSAGE);

                invoicesList.removeAll();
                invoicesList.createInvoicesListPage();
                invoicesList.saveCurrentTableData();
                invoicesList.createListeners();
                invoicesList.revalidate();
                invoicesList.repaint();
            } else {
                JOptionPane.showMessageDialog(new JPanel(), "Delete Invoice " + invoicesList.tableData[clickedRow][2]
                        + " Failed!", "Notice", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}

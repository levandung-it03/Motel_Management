package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Invoices.Page_InvoicesList;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Invoices.Dialog_InvoicesOfRoom;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.GeneralCentralPanelListeners;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class InvoicesListListeners {
    public InvoicesListListeners() { super(); }

    public static MouseAdapter getAllInvoicesByMouseListener(DefaultTableModel defaultModel, JTable table,
                                                             Page_InvoicesList invoicesList, JFrame mainFrameApp) {
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

    public static void viewAllInvoice(String roomId, JFrame mainFrameApp, Page_InvoicesList invoicesList) {
        Dialog_InvoicesOfRoom invoicesOfRoom = new Dialog_InvoicesOfRoom(mainFrameApp, roomId, invoicesList);
    }

    public static void deleteInvoice(int clickedRow, Page_InvoicesList invoicesList, DefaultTableModel defaultModel) {
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

    public static KeyListener searchTableToGetObjects(Page_InvoicesList page) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // Make page.tableData Update Continuous.
                GeneralCentralPanelListeners.searchTableToGetObjects(page.searchingTextField, page.searchingComboBox, page.table,
                        page.tableData, page.defaultModel);
            }
        };
    }

}

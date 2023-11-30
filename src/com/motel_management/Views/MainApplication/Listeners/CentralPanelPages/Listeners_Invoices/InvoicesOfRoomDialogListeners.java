package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.Dialog_InvoiceDetail;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.SubItem_InvoicePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InvoicesOfRoomDialogListeners {
    public InvoicesOfRoomDialogListeners() {}

    public static ActionListener viewDetailInvoice(InvoiceModel invoice, JDialog parentDialog) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog_InvoiceDetail detailDialog =
                        new Dialog_InvoiceDetail(Controller_Invoices.getInvoiceByInvoiceId(invoice.getInvoiceId()), parentDialog);
            }
        };
    }

    public static ActionListener updateInvoiceStatus(InvoiceModel invoice, SubItem_InvoicePanel rootPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm This Updating Action?", "Notice",
                JOptionPane.YES_NO_OPTION) != 0)
                    return;

                HashMap<String, String> result = Controller_Invoices.updateInvoiceStatus(invoice.getInvoiceId());
                JOptionPane.showMessageDialog(new JPanel(), result.get("message"), "Notice", JOptionPane.PLAIN_MESSAGE);

                if (result.get("result").equals("1")) {
                    rootPanel.removeAll();
                    rootPanel.invoice.setWasPaid(invoice.getWasPaid().equals("0") ? "1" : "0");
                    rootPanel.createInvoicePanel();
                    rootPanel.revalidate();
                    rootPanel.repaint();
                }
            }
        };
    }
}

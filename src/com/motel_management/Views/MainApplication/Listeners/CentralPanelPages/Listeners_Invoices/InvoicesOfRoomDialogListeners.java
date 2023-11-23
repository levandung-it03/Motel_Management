package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices;

import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices.InvoiceDetailDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoicesOfRoomDialogListeners {
    public InvoicesOfRoomDialogListeners() {}

    public static ActionListener viewDetailInvoice(InvoiceModel invoice, JDialog parentDialog) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvoiceDetailDialog detailDialog = new InvoiceDetailDialog(invoice, parentDialog);
            }
        };
    }
}

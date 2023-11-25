package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_CheckOut;

import com.motel_management.Controllers.Controller_Electricity_Water;
import com.motel_management.Controllers.Controllers_Checkout;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckOutPage extends JPanel {
    public JTable checkOutTable;
    public JScrollPane checkOutScrollPane;
    public DefaultTableModel defaultCheckoutTable;
    public CheckOutPage() {
        super(new BorderLayout());
        this.createCheckOutHistoryPanel();
    }
    public void createCheckOutHistoryPanel() {
        JLabel title = new JLabel("Check-Out History");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.

        String[][] checkout = Controllers_Checkout.getCheckOutHistory();
        String[] columns = {"Check-out ID", "Contract ID", "Check-out Date", "Reason"};

        // Generate Table.
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(checkout, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultCheckoutTable = tableAsList.getDefaultModel();
        this.checkOutTable = tableAsList.getTable();
        this.checkOutScrollPane = tableAsList.getScrollPane();

        // Margin Table.
        this.checkOutScrollPane.setBorder(new EmptyBorder(20, 50, 0, 50));

        // Resize several Columns.
        this.checkOutTable.getColumnModel().getColumn(3).setPreferredWidth(400);


        add(checkOutScrollPane);
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Controllers.Controller_Invoices;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InvoicesOfRoomDialog extends JDialog {
    JFrame mainFrameApp;
    InvoicesListPage invoicesList;
    private final String roomId;

    public ArrayList<InvoicePanelItem> invoicePanels = new ArrayList<>();
    JPanel mainPanel = new JPanel();
    JPanel toolPanel = new JPanel(new BorderLayout());

    ArrayList<InvoiceModel> invoices = new ArrayList<>();
    int currentPage = 0;
    private final JButton newInvoicesBtn = new JButton("<< New");
    private final JButton oldInvoicesBtn = new JButton("Old >>");

    public InvoicesOfRoomDialog(JFrame mainFrameApp, String roomId, InvoicesListPage invoicesList) {
        super(mainFrameApp, "Invoices");
        this.mainFrameApp = mainFrameApp;
        this.invoicesList = invoicesList;
        this.roomId = roomId;

        this.createOnSiteListener();
        this.createInvoicesOfMainRoomDialog();
    }

    public void createInvoicesOfMainRoomDialog() {
        int toolHeight = 50;
        this.setLayout(new BorderLayout());
        this.setModal(true);
        this.setSize(Configs.centralPanelWidth + 200, Configs.centralPanelHeight + toolHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel newInvoicesPanel = new JPanel(new FlowLayout());
        this.newInvoicesBtn.setPreferredSize(new Dimension(100, 35));
        this.newInvoicesBtn.setFont(Configs.labelFont);
        this.newInvoicesBtn.setBackground(new Color(145, 217, 136));
        this.newInvoicesBtn.setFont(this.newInvoicesBtn.getFont().deriveFont(18.0f));
        newInvoicesPanel.setBorder(new EmptyBorder(0, 15, 0, 0));

        JPanel title = new JPanel();
        JLabel titleLabel = new JLabel("Invoices");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 34.0f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        title.add(titleLabel);
        toolPanel.setPreferredSize(new Dimension(Configs.centralPanelWidth + 200, toolHeight));

        JPanel oldInvoicesPanel = new JPanel(new FlowLayout());
        this.oldInvoicesBtn.setPreferredSize(new Dimension(100, 35));
        this.oldInvoicesBtn.setFont(Configs.labelFont);
        this.oldInvoicesBtn.setBackground(new Color(139, 195, 211));
        this.oldInvoicesBtn.setFont(this.oldInvoicesBtn.getFont().deriveFont(18.0f));
        oldInvoicesPanel.setBorder(new EmptyBorder(0, 0, 0, 15));

        newInvoicesPanel.add(this.newInvoicesBtn);
        oldInvoicesPanel.add(this.oldInvoicesBtn);
        toolPanel.add(title, BorderLayout.CENTER);
        toolPanel.add(newInvoicesPanel, BorderLayout.WEST);
        toolPanel.add(oldInvoicesPanel, BorderLayout.EAST);

        this.add(toolPanel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.createInvoicesOfRoom();
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void createInvoicesOfRoom() {
        if (this.invoices != null) {
            mainPanel.removeAll();
            this.invoices.clear();
            invoicePanels.clear();
        }

        this.invoices = Controller_Invoices.getInvoicesByRoomIdWithPage(this.currentPage, roomId);
        for (int i = 0; i < invoices.size(); i++)
            invoicePanels.add(new InvoicePanelItem(i, invoices.get(i), this));

        invoicePanels.forEach(panel -> mainPanel.add(panel));
        for (int i = 12 - invoicePanels.size(); i > 0; i--) {
            JPanel empty = new JPanel();
            empty.setPreferredSize(new Dimension(270, 100));
            mainPanel.add(empty);
        }
    }

    public void createOnSiteListener() {
//         Update Last Rooms Invoices When Dialog Closed.
        this.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e) {}
            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {
                invoicesList.removeAll();
                invoicesList.createInvoicesListPage();
                invoicesList.createListeners();
                invoicesList.revalidate();
                invoicesList.repaint();
                return;
            }
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
        });

        // Change Current Page
        this.newInvoicesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage == 0) {
                    JOptionPane.showMessageDialog(new JPanel(), "There's No Newer Invoices!");
                    return;
                }

                currentPage--;
                createInvoicesOfRoom();
                revalidate();
                repaint();
            }
        });

        this.oldInvoicesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invoices.size() < 12) {
                    JOptionPane.showMessageDialog(new JPanel(), "There's No Older Invoices!");
                    return;
                }

                currentPage++;
                createInvoicesOfRoom();
                revalidate();
                repaint();
            }
        });
    }
}

package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract;

import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Dialog_DetailContract extends JDialog {
    private final ContractModel contract;
    private final String fullName;
    public Dialog_DetailContract(Frame_MainApplication mainFrameApp, ContractModel contract, String fullName) {
        super(mainFrameApp, "Detail Contract");
        this.contract = contract;
        this.fullName = fullName;
        this.createDetailContract();
    }

    public void createDetailContract() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        int fullHeight = 490;
        int fullWith = 580;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.setSize(fullWith, fullHeight);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JLabel titleLabel = new JLabel("ID - " + this.contract.getContractId());
        titleLabel.setFont(Configs.labelFont);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 34.0f));

        JPanel title = new JPanel();
        title.setPreferredSize(new Dimension(fullWith, 90));
        title.setBorder(new EmptyBorder(30, 0, 25, 0));
        title.add(titleLabel);
        this.add(title, BorderLayout.NORTH);


        JPanel leftColumn = new JPanel(new GridLayout(11, 0));
        leftColumn.add(new JLabel("Room Code:"));
        leftColumn.add(new JLabel("Checked Out:"));
        leftColumn.add(new JLabel("Identify Card:"));
        leftColumn.add(new JLabel("Full Name:"));
        leftColumn.add(new JLabel("Origin Quantity:"));
        leftColumn.add(new JLabel("Room Deposit:"));
        leftColumn.add(new JLabel("Is A Family:"));
        leftColumn.add(new JLabel("Starting Date:"));
        leftColumn.add(new JLabel("Ending Date:"));
        leftColumn.add(new JLabel("Total Occupying Months:"));
        leftColumn.add(new JLabel("Register Temporary Address:"));

        JPanel rightColumn = new JPanel(new GridLayout(11, 0));
        rightColumn.add(new JLabel(this.contract.getRoomId()));
        rightColumn.add(new JLabel(this.contract.getCheckedOut().equals("0") ? "NO" : "YES"));
        rightColumn.add(new JLabel(this.contract.getIdentifier()));
        rightColumn.add(new JLabel(this.fullName));
        rightColumn.add(new JLabel(Integer.toString(this.contract.getQuantity())));
        rightColumn.add(new JLabel(Configs.convertStringToVNDCurrency(this.contract.getRoomDeposit())));
        rightColumn.add(new JLabel(this.contract.getIsFamily().equals("0") ? "NO" : "YES"));
        rightColumn.add(new JLabel(sdf.format(this.contract.getStartingDate())));
        rightColumn.add(new JLabel(sdf.format(this.contract.getEndingDate())));
        rightColumn.add(new JLabel(Integer.toString((this.contract.getTotalMonths()))));
        rightColumn.add(new JLabel(this.contract.getIsRegisteredPerAddress().equals("0") ? "NO" : "YES"));

        leftColumn.setPreferredSize(new Dimension((int)(fullWith*0.52), fullHeight));
        rightColumn.setPreferredSize(new Dimension((int)(fullWith*0.48), fullHeight));

        leftColumn.setBorder(new EmptyBorder(0, 55, 50, 0));
        rightColumn.setBorder(new EmptyBorder(0, 0, 50, 55));

        this.add(leftColumn, BorderLayout.WEST);
        this.add(rightColumn, BorderLayout.EAST);

        this.add(leftColumn, BorderLayout.WEST);
        this.add(rightColumn, BorderLayout.CENTER);
        this.setVisible(true);
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ContractPage extends JPanel {
    public static JTabbedPane mainPage;
    private JPanel contractListPanel;
    private JPanel addContractPanel;
    
    // Constructor
    public ContractPage() {
        // Set Layout Here
        super();
        this.createContractPanel();
        this.createOnsiteListeners();
    }

    public void createContractPanel() {
        mainPage = new JTabbedPane(JTabbedPane.TOP);
        contractListPanel = new ContractListPage();
        addContractPanel = new AddContractPage();

        contractListPanel.setBackground(Configs.mainWhiteBackground);
        addContractPanel.setBackground(Configs.mainWhiteBackground);

        mainPage.add("Contracts List", contractListPanel);
        mainPage.add("Add New Contract", addContractPanel);

        add(mainPage);
    }
    
    public void createOnsiteListeners() {
        mainPage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainPage.getSelectedIndex() == 0) {
                    contractListPanel = new ContractListPage();
                    mainPage.setComponentAt(0, contractListPanel);
                } else {
                    addContractPanel = new AddContractPage();
                    mainPage.setComponentAt(1, addContractPanel);
                }
            }
        });
    }
}
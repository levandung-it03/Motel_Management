package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_ContractMain extends JPanel {
    private JTabbedPane mainTabbedPane;
    private JPanel contractListPanel;
    private JPanel addContractPanel;

    // Getters


    public JTabbedPane getMainTabbedPane() { return mainTabbedPane; }

    // Constructor
    public Page_ContractMain() {
        // Set Layout Here
        super();
        this.createContractPanel();
        this.createOnsiteListeners();
    }

    public void createContractPanel() {
        mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contractListPanel = new Page_ContractList();
        addContractPanel = new Page_AddContract(this);

        contractListPanel.setBackground(Configs.mainWhiteBackground);
        addContractPanel.setBackground(Configs.mainWhiteBackground);

        mainTabbedPane.add("Contracts List", contractListPanel);
        mainTabbedPane.add("Add New Contract", addContractPanel);

        add(mainTabbedPane);
    }
    
    public void createOnsiteListeners() {
        Page_ContractMain _this = this;
        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainTabbedPane.getSelectedIndex() == 0) {
                    contractListPanel = new Page_ContractList();
                    mainTabbedPane.setComponentAt(0, contractListPanel);
                } else {
                    addContractPanel = new Page_AddContract(_this);
                    mainTabbedPane.setComponentAt(1, addContractPanel);
                }
            }
        });
    }
}
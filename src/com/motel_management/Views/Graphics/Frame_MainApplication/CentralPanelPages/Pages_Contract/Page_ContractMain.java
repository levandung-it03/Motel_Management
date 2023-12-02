package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Contract;

import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Page_ContractMain extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    private JTabbedPane mainTabbedPane;
    private JPanel contractListPanel;
    private JPanel addContractPanel;

    // Getters


    public JTabbedPane getMainTabbedPane() { return mainTabbedPane; }

    // Constructor
    public Page_ContractMain(Frame_MainApplication mainFrameApp) {
        // Set Layout Here
        super();
        this.mainFrameApp = mainFrameApp;
        this.createContractPanel();
        this.createOnsiteListeners();
    }

    public void createContractPanel() {
        mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contractListPanel = new Page_ContractList(this.mainFrameApp);
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
                    contractListPanel = new Page_ContractList(_this.mainFrameApp);
                    mainTabbedPane.setComponentAt(0, contractListPanel);
                } else {
                    addContractPanel = new Page_AddContract(_this);
                    mainTabbedPane.setComponentAt(1, addContractPanel);
                }
            }
        });
    }
}
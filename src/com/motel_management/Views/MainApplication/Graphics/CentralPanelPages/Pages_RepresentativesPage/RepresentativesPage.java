package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RepresentativesPage extends JPanel {
    public static JPanel mainPage;
    private JPanel representativesListPanel;
    // Constructor
    public RepresentativesPage() {
        // Set Layout Here
        super();
        this.createRepresentativesPanel();
    }


    public void createRepresentativesPanel(){
        mainPage = new JPanel();
        representativesListPanel =new RepresentativesListPage();
        mainPage.add(representativesListPanel);
        add(mainPage);
    }
}

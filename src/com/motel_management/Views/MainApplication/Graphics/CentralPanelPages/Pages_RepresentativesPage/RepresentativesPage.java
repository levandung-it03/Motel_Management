package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import javax.swing.*;
import java.awt.*;

public class RepresentativesPage extends JPanel{
    JFrame mainAppFrame;
    private JPanel personListPage;
    public RepresentativesPage() {
        super(new BorderLayout());
        this.mainAppFrame = mainAppFrame;
        this.CreateRepresentativesPanel();
    }
    public void CreateRepresentativesPanel(){
        personListPage = new RepresentativesListPage(mainAppFrame);
        add(personListPage);
    }
}


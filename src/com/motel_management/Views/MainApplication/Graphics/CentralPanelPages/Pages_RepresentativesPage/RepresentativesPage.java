package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage;

import javax.swing.*;
import java.awt.*;

public class RepresentativesPage extends JPanel{
    private JPanel personListPage;
    public RepresentativesPage() {
        super(new BorderLayout());
        this.CreateRepresentativesPanel();
    }
    public void CreateRepresentativesPanel(){
        personListPage = new RepresentativesListPage();
        add(personListPage);
    }
}


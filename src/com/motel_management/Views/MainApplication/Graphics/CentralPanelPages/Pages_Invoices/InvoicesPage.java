package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Invoices;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.LabelAsColumnName;

import javax.swing.*;
import java.awt.*;

public class InvoicesPage extends JPanel {
    JPanel mainContainer = new JPanel(new BorderLayout());
    JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel tablesContainer = new JPanel(new GridLayout());
    JPanel labelsContainer = new JPanel();

    JPanel mainHeaderLabel = new JPanel();

    // Constructor
    public InvoicesPage() {
        // Set Layout Here
        super();
        this.createCheckOutPanel();
    }

    public void createCheckOutPanel() {
        mainContainer.setBackground(Color.blue);
        tablesContainer.setBackground(Color.red);
        this.createHeader();

        mainContainer.add(header, BorderLayout.NORTH);
        mainContainer.add(tablesContainer, BorderLayout.CENTER);

        JScrollPane scrollPaneContainer = new JScrollPane(mainContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneContainer.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneContainer.setPreferredSize(
                new Dimension(Configs.centralPanelWidth + 20, Configs.centralPanelHeight + 35));

        add(scrollPaneContainer);
    }

    public void createHeader() {
        mainHeaderLabel.add(LabelAsColumnName.createHeaderLabel("Room Code \\ Month"));
        mainHeaderLabel.setBackground(new Color(255, 255, 91));

        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Jan"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Feb"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Mar"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Apr"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("May"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Jun"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Jul"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Org"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Sep"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Oct"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Nov"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Dec"));
        labelsContainer.add(LabelAsColumnName.createHeaderLabel("Jan"));

        header.add(mainHeaderLabel);
        header.add(labelsContainer);
    }
}

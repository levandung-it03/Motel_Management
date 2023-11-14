
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StatisticPage extends JPanel {
    ArrayList<JPanel> tags = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    // Constructor
    public StatisticPage() {
        // Set Layout Here
        super(new BorderLayout());
        this.createStatisticPanel();
    }

    public void createStatisticPanel() {
        JPanel overviewPanel = new JPanel(new GridLayout(0,4,10,10));
        JPanel test1 = new JPanel();

        tags.add(new JPanel(new GridLayout(2,0)));
        tags.add(new JPanel(new GridLayout(2,0)));
        tags.add(new JPanel(new GridLayout(2,0)));
        tags.add(new JPanel(new GridLayout(2,0)));
        tags.add(new JPanel(new GridLayout(2,0)));

        labels.add(new JLabel("Person"));
        labels.add(new JLabel("Room"));
        labels.add(new JLabel("User"));
        labels.add(new JLabel("Gura"));
        labels.add(new JLabel("Gura"));

        for (int i=0;i<tags.size();i++){


            JLabel number = new JLabel("12");
            number.setFont(number.getFont().deriveFont(30.0f));
            number.setFont(number.getFont().deriveFont(Font.BOLD));
            number.setForeground(Color.white);
            number.setBorder(new EmptyBorder(30,10,0,10));

            labels.get(i).setFont(labels.get(i).getFont().deriveFont(26.0f));
            labels.get(i).setForeground(Color.white);
            labels.get(i).setBorder(new EmptyBorder(0,10,10,10));
            labels.get(i).setFont(labels.get(i).getFont().deriveFont(Font.BOLD));

            tags.get(i).add(number);
            tags.get(i).add(labels.get(i));
            tags.get(i).setBackground(Configs.normalGreen);
            overviewPanel.add(tags.get(i));
        }


        overviewPanel.setBackground(Color.lightGray);
        overviewPanel.setPreferredSize(new Dimension(10,400));
        test1.setBackground(Color.gray);
        overviewPanel.setBorder(new EmptyBorder(10,10,10,40));
        add(overviewPanel,BorderLayout.NORTH);
        add(test1,BorderLayout.CENTER);


    }
}

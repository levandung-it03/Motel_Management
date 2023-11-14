
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticPage extends JPanel {
    // Constructor
    public StatisticPage() {
        // Set Layout Here
<<<<<<< HEAD
        super(new BorderLayout());
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
=======
        super();
>>>>>>> a7b79d869bc86c008b792c4d09255635117dcea6
        this.createStatisticPanel();
    }

    public void createStatisticPanel() {
        JPanel test = new JPanel(new GridLayout(0,4,10,10));
        JPanel test1 = new JPanel();
        for(int i=0;i<6;i++){
            JPanel a = new JPanel();
            a.setBounds(0,0,30,30);
            a.setBackground(Configuration.lightGreen);
            test.add(a);
        }
        test.setBackground(Color.lightGray);
        test.setPreferredSize(new Dimension(100,400));
        test1.setBackground(Color.gray);
        test.setBorder(new EmptyBorder(10,10,10,10));
        add(test,BorderLayout.NORTH);
        add(test1,BorderLayout.CENTER);


    }
}

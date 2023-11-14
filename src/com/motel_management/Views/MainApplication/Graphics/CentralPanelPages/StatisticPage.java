<<<<<<< HEAD:src/com/motel_management/Views/MainApplication/Panel/CentralPanels/Statistic.java
package com.motel_management.Views.MainApplication.Panel.CentralPanels;
import com.motel_management.Views.Configuration;

=======
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
>>>>>>> 1041e489a9acb4e04eff459d83ab6ec6a9f91cb1:src/com/motel_management/Views/MainApplication/Graphics/CentralPanelPages/StatisticPage.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticPage extends JPanel {
    private final int centralPanelWidth;
    private final int centralPanelHeight;
    // Constructor
    public StatisticPage(int centralPanelWidth, int centralPanelHeight) {
        // Set Layout Here
        super(new GridLayout(2,4,10,10));
        this.centralPanelWidth = centralPanelWidth;
        this.centralPanelHeight = centralPanelHeight;
        this.createStatisticPanel();
    }

    public void createStatisticPanel() {

        for(int i=0;i<6;i++){
            JPanel a = new JPanel();
            a.setPreferredSize(new Dimension(30,30));
            a.setBackground(Configuration.lightGreen);
            add(a);

        }
    }
}

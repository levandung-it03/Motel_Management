
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import com.motel_management.Controllers.Controller_Statistic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StatisticPage extends JPanel {
    ArrayList<JPanel> tags = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>();
    ArrayList<JLabel> icons = new ArrayList<>();
    // Constructor
    public StatisticPage() {
        // Set Layout Here
        super(new BorderLayout());
        this.createStatisticPanel();
    }
    public JPanel generateTagPanel(String name,int quantity) {
        JPanel tag = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(2,0));
        JLabel quantityTag = new JLabel(String.valueOf(quantity));
        JLabel nameTag = new JLabel(name);

        quantityTag.setFont(quantityTag.getFont().deriveFont(Font.BOLD,26.0f));
        quantityTag.setForeground(Color.white);
        quantityTag.setBorder(new EmptyBorder(30,10,0,10));

        nameTag.setFont(nameTag.getFont().deriveFont(Font.BOLD,24.0f));
        nameTag.setForeground(Color.white);
        nameTag.setBorder(new EmptyBorder(0,10,10,10));

        infoPanel.add(quantityTag);
        infoPanel.add(nameTag);
        infoPanel.setOpaque(false);

        tag.add(infoPanel,BorderLayout.WEST);
        return tag;
    }

    public void createStatisticPanel() {
        setBackground(new Color(228, 230, 236));
        JPanel overviewPanel = new JPanel(new GridLayout(0,4,10,10));
        overviewPanel.setPreferredSize(new Dimension(0,350));
        overviewPanel.setOpaque(false);

        tags.add(generateTagPanel("Person",Controller_Statistic.getTotalPerson()));
        tags.add(generateTagPanel("Room", Controller_Statistic.getTotalRoom()));
        tags.add(generateTagPanel("Account",Controller_Statistic.getTotalAccount()));
        tags.add(generateTagPanel("Revenue",100000000));
        tags.add(generateTagPanel("Gura",3000));

        colors.add(new Color(0,190,237));
        colors.add(new Color(255,133,26));
        colors.add(new Color(243, 155, 17));
        colors.add(new Color(218, 74, 56));
        colors.add(new Color(1, 165, 89));

        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/person.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/rooms.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/users.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/revenue.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/person.png")));

        for (int i=0;i<tags.size();i++){
            tags.get(i).setBackground(colors.get(i));
            JPanel iconPanel = new JPanel(new BorderLayout());
            icons.get(i).setHorizontalAlignment(JLabel.RIGHT);
            icons.get(i).setBorder(new EmptyBorder(0,0,0,20));

            iconPanel.add(icons.get(i));
            iconPanel.setOpaque(false);
            iconPanel.add(icons.get(i));
            tags.get(i).add(iconPanel,BorderLayout.CENTER);
            overviewPanel.add(tags.get(i));
        }

        // Second Panel showing list of rooms and revenue
        JPanel list = new JPanel(new GridLayout(0,2,10,10));
        list.setOpaque(false);
        JPanel roomList = new JPanel(new BorderLayout());
        JPanel revenue = new JPanel(new BorderLayout());
        JLabel a = new JLabel("Danh Sach Phong");
        a.setHorizontalAlignment(JLabel.CENTER);
        roomList.add(a,BorderLayout.NORTH);
        JLabel b = new JLabel("Thong Ke Doanh Thu");
        b.setHorizontalAlignment(JLabel.CENTER);
        revenue.add(b,BorderLayout.NORTH);
        list.add(roomList);
        list.add(revenue);

        overviewPanel.setBorder(new EmptyBorder(10,10,10,40));

        add(overviewPanel,BorderLayout.NORTH);
        add(list,BorderLayout.CENTER);

    }
}

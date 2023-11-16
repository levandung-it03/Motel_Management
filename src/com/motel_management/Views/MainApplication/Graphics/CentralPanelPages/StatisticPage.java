
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;

import com.motel_management.Controllers.Controller_RoomList;
import com.motel_management.Controllers.Controller_Statistic;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public JPanel generateTagPanel(String name, int quantity) {
        JPanel tag = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(2, 0));
        JLabel quantityTag = new JLabel(String.valueOf(quantity));
        JLabel nameTag = new JLabel(name);

        quantityTag.setFont(quantityTag.getFont().deriveFont(Font.BOLD, 26.0f));
        quantityTag.setForeground(Color.white);
        quantityTag.setBorder(new EmptyBorder(30, 10, 0, 10));

        nameTag.setFont(nameTag.getFont().deriveFont(Font.BOLD, 24.0f));
        nameTag.setForeground(Color.white);
        nameTag.setBorder(new EmptyBorder(0, 10, 10, 10));

        infoPanel.add(quantityTag);
        infoPanel.add(nameTag);
        infoPanel.setOpaque(false);

        tag.add(infoPanel, BorderLayout.WEST);
        return tag;
    }

    public void createStatisticPanel() {
        setBackground(new Color(228, 230, 236));
        JPanel overviewPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        overviewPanel.setPreferredSize(new Dimension(0, 200));
        overviewPanel.setOpaque(false);

        tags.add(generateTagPanel("Person", Controller_Statistic.getTotalPerson()));
        tags.add(generateTagPanel("Room", Controller_Statistic.getTotalRoom()));
        tags.add(generateTagPanel("Account", Controller_Statistic.getTotalAccount()));
        tags.add(generateTagPanel("Revenue", 100000000));

        colors.add(new Color(0, 190, 237));
        colors.add(new Color(255, 133, 26));
        colors.add(new Color(243, 155, 17));
        colors.add(new Color(218, 74, 56));

        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/person.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/rooms.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/users.png")));
        icons.add(new JLabel(new ImageIcon("src/com/motel_management/Assets/img/revenue.png")));

        for (int i = 0; i < tags.size(); i++) {
            tags.get(i).setBackground(colors.get(i));
            JPanel iconPanel = new JPanel(new BorderLayout());
            icons.get(i).setHorizontalAlignment(JLabel.RIGHT);
            icons.get(i).setBorder(new EmptyBorder(0, 0, 0, 20));

            iconPanel.add(icons.get(i));
            iconPanel.setOpaque(false);
            iconPanel.add(icons.get(i));
            tags.get(i).add(iconPanel, BorderLayout.CENTER);
            overviewPanel.add(tags.get(i));
        }

        // Second Panel showing list of room list and revenue
        JPanel secondPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        secondPanel.setOpaque(false);
        secondPanel.add(createRoomListPanel());
        secondPanel.add(createRevenuePanel());

        overviewPanel.setBorder(new EmptyBorder(10, 10, 30, 10));

        add(overviewPanel, BorderLayout.NORTH);
        add(secondPanel, BorderLayout.CENTER);
    }

    public JPanel createRoomListPanel() {
        JPanel roomList = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Room List");
        title.setFont(title.getFont().deriveFont(Font.BOLD,34.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        roomList.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        String[][] rooms = Controller_Statistic.getRoomList();
        String[] columns = {"Room Code", "Representative","Quantity", "Default Room Price"};
        HashMap<Integer,Integer> resizeColumnList = new HashMap<>();
//        resizeColumnList.put();

        // Generate Table.
        JScrollPane roomScrollPane = this.createTableAsList(rooms, columns, resizeColumnList);

        // Margin Table.
        roomScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        roomList.add(roomScrollPane);
        return roomList;
    }

    public JPanel createRevenuePanel() {
        JPanel revenue = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Revenue Statistics");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        revenue.add(title, BorderLayout.NORTH);
        return revenue;
    }

    public JScrollPane createTableAsList(String[][] rows, String[] columns,HashMap<Integer,Integer> resizeColumnList) {


        // Create a Table Model (with all Unchangeable).
        DefaultTableModel defaultModel = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create Table
        JTable table = new JTable(defaultModel);
        table.setBorder(new LineBorder(Configs.blackTextColor, 1, true));

        // Set Header (Column Name) Font.
        JTableHeader header = table.getTableHeader();
        header.setFont(Configs.labelFont);
        table.setFont(Configs.labelFont);

        // Set Table size.
        table.setRowHeight(30);

        // Make all Columns align horizontally.
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setVerticalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount() - 1; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        // Resize several Columns.
        resizeColumnList.forEach((k,v)->{
            table.getColumnModel().getColumn(k).setPreferredWidth(v);
        });


        // Create ScrollPane to Cover JTable.
        return new JScrollPane(table);
    }
}
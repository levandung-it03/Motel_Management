
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages;
import com.motel_management.Controllers.Controller_Statistic;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
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

        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel quantityTag = new JLabel(String.valueOf(quantity));
        JLabel nameTag = new JLabel(name);

        quantityTag.setBorder(new EmptyBorder(40, 10, 0, 10));
        if(name.equalsIgnoreCase("revenue")){
            quantityTag = new JLabel("<html>"+
                    Configs.convertStringToVNDCurrency(String.valueOf(quantity)).replace("VNĐ","<br>VNĐ</html>")
            );
            quantityTag.setBorder(new EmptyBorder(30, 10, 0, 10));
        }
        quantityTag.setFont(quantityTag.getFont().deriveFont(Font.BOLD, 26.0f));
        quantityTag.setForeground(Color.white);

        nameTag.setFont(nameTag.getFont().deriveFont(Font.BOLD, 26.0f));
        nameTag.setForeground(Color.white);
        nameTag.setBorder(new EmptyBorder(0, 10, 20, 10));

        infoPanel.add(quantityTag,BorderLayout.NORTH);
        infoPanel.add(nameTag,BorderLayout.SOUTH);
        infoPanel.setOpaque(false);

        JPanel timePanel = new JPanel();
        LocalDate time = LocalDate.now();
        JLabel currentTime = new JLabel(time.getDayOfMonth()+"/"+time.getMonthValue()+"/"+time.getYear());
        currentTime.setFont(currentTime.getFont().deriveFont(Font.BOLD, 18.0f));
        currentTime.setForeground(Color.white);
        currentTime.setHorizontalAlignment(JLabel.CENTER);
        timePanel.add(currentTime);
        timePanel.setBackground(new Color(0,0,0, 40));
        timePanel.setPreferredSize(new Dimension(0,30));

        tag.add(infoPanel, BorderLayout.WEST);
        tag.add(timePanel,BorderLayout.SOUTH);
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
    tags.add(generateTagPanel("Revenue", Controller_Statistic.getTotalRevenue()));

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

            tags.get(i).add(iconPanel, BorderLayout.CENTER);
            overviewPanel.add(tags.get(i));
        }

        // Second Panel showing list of room list and revenue
        JPanel secondPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        secondPanel.setOpaque(false);
        secondPanel.add(createRoomListPanel());
        secondPanel.add(createRevenuePanel());

        overviewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(overviewPanel, BorderLayout.NORTH);
        add(secondPanel, BorderLayout.CENTER);
    }

    public JPanel createRoomListPanel() {
        JPanel roomListPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Occupied Room");
        title.setFont(title.getFont().deriveFont(Font.BOLD,29));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        roomListPanel.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        String[][] rooms = Controller_Statistic.getRoomList();
        String[] columns = {"Room Code", "Representative","Quantity", "Room Price"};
        HashMap<Integer,Integer> resizeColumnList = new HashMap<>();
        resizeColumnList.put(0,30);
        resizeColumnList.put(2,30);


        // Generate Table.
        JScrollPane roomScrollPane = this.createTableAsList(rooms, columns, resizeColumnList);

        // Margin Table.
        roomScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        roomListPanel.add(roomScrollPane);
        return roomListPanel;
    }

    public JPanel createRevenuePanel() {
        JPanel revenuePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Monthly Revenue");
        title.setFont(title.getFont().deriveFont(Font.BOLD,29));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));
        revenuePanel.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        Object[][] revenue = Controller_Statistic.getRevenue();
        String[] columns = {"Month", "Revenue"};
        HashMap<Integer,Integer> resizeColumnList = new HashMap<>();
        //resizeColumnList.put();

        // Generate Table.
        JScrollPane roomScrollPane = this.createTableAsList(revenue, columns, resizeColumnList);

        // Margin Table.
        roomScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        revenuePanel.add(roomScrollPane);
        return revenuePanel;
    }

    public JScrollPane createTableAsList(Object[][] rows, String[] columns,HashMap<Integer,Integer> resizeColumnList) {

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
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        // Resize several Columns.
        resizeColumnList.forEach((k,v)-> table.getColumnModel().getColumn(k).setPreferredWidth(v));


        // Create ScrollPane to Cover JTable.
        return new JScrollPane(table);
    }
}
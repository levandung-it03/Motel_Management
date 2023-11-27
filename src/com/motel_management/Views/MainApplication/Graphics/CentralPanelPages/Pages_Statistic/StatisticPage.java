
package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Statistic;
import com.motel_management.Controllers.Controller_Statistic;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.TableAsList;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_Statistic.StatisticListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Invoices.InvoicesListListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StatisticPage extends JPanel {
    ArrayList<JPanel> tags = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>();
    ArrayList<JLabel> icons = new ArrayList<>();
    JFrame mainFrameApp;
    DefaultTableModel defaultRoomTable;
    JTable roomTable;
    JScrollPane roomScrollPane;
    DefaultTableModel defaultStatisticTable;
    JTable statisticTable;
    JScrollPane statisticScrollPane;

    // Constructor
    public StatisticPage(JFrame mainFrameApp) {
        // Set Layout Here
        super(new BorderLayout());
        this.mainFrameApp=mainFrameApp;
        this.createStatisticPanel();
        this.createListener();
    }

    public void createStatisticPanel() {
        setBackground(new Color(228, 230, 236));
        JPanel overviewPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        overviewPanel.setPreferredSize(new Dimension(0, 180));
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
        Object[][] rooms = Controller_Statistic.getRoomList();
        String[] columns = {"Room Code", "Representative","Quantity","Price"};
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(rooms, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultRoomTable = tableAsList.getDefaultModel();
        this.roomTable = tableAsList.getTable();
        this.roomScrollPane = tableAsList.getScrollPane();

        // Resize several Columns.
        this.roomTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.roomTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        this.roomTable.getColumnModel().getColumn(3).setPreferredWidth(40);

        roomScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        roomListPanel.add(roomScrollPane);
        return roomListPanel;
    }

    public JPanel createRevenuePanel() {
        JPanel revenuePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Statistic");
        title.setFont(title.getFont().deriveFont(Font.BOLD,29));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 0, 10));

        revenuePanel.add(title, BorderLayout.NORTH);

        // Prepare Data to generate Table.
        Object[][] revenue = Controller_Statistic.getRevenue();
        String[] columns = {"Year","Revenue","Profit","Detail"};
        TableAsList tableAsList = new TableAsList(new DefaultTableModel(revenue, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.defaultStatisticTable = tableAsList.getDefaultModel();
        this.statisticTable = tableAsList.getTable();
        this.statisticScrollPane = tableAsList.getScrollPane();

        // Resize several Columns.
        this.statisticTable.getColumnModel().getColumn(0).setPreferredWidth(20);

        statisticScrollPane.setBorder(new EmptyBorder(0, 20, 20,20));
        revenuePanel.add(statisticScrollPane);
        return revenuePanel;
    }
    public JPanel generateTagPanel(String name, int quantity) {
        JPanel tag = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel quantityTag = new JLabel(String.valueOf(quantity));
        JLabel nameTag = new JLabel(name);

        quantityTag.setBorder(new EmptyBorder(30, 10, 0, 10));
        if(name.equalsIgnoreCase("revenue")){
            quantityTag = new JLabel("<html>"+
                    Configs.convertStringToVNDCurrency(String.valueOf(quantity)).replace("VNĐ","<br>VNĐ</html>")
            );
            quantityTag.setBorder(new EmptyBorder(20, 10, 0, 10));
        }
        quantityTag.setFont(quantityTag.getFont().deriveFont(Font.BOLD, 26.0f));
        quantityTag.setForeground(Color.white);

        nameTag.setFont(nameTag.getFont().deriveFont(Font.BOLD, 26.0f));
        nameTag.setForeground(Color.white);
        nameTag.setBorder(new EmptyBorder(0, 10, 10, 10));

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


    public void createListener(){
        statisticTable.addMouseListener(
                StatisticListeners.getDetailProfit(mainFrameApp)
        );
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_Room.RoomListeners;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RoomPage extends JPanel {
    JScrollPane roomScrollPane;
    JFrame mainFrameApp;
    JPanel addContainer;
    JPanel roomContainer;
    JPanel functionContainer;
    JPanel tag;
    JTextField roomIdInp = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);

    JButton submitBtn;
    JTextField searchRoomId = new JTextField(20);
    JButton searchBtn;
    JPopupMenu popupMenu;
    String[][] data;

    public RoomPage(JFrame mainFrameApp) {
        super(new BorderLayout());
        this.mainFrameApp = mainFrameApp;
        this.createAddRoomsPanel();
        this.createRoomsPanel();
        this.createFunctionsPanel();
        this.createAddRoomListeners();
        this.createFunctionsListeners();
    }

    public void createAddRoomsPanel() {
        addContainer = new JPanel(new FlowLayout());
        addContainer.setPreferredSize(new Dimension(0, 80));
        addContainer.setBorder(new LineBorder(Color.black));

        this.submitBtn = InputComboPanel.generateButton("Add Room");
        addContainer.add(InputComboPanel.generateTextInputPanel("Room Code", this.roomIdInp));
        addContainer.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", this.maxQuantity));
        addContainer.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", this.defaultPrice));
        addContainer.add(this.submitBtn);

        add(addContainer, BorderLayout.NORTH);
    }

    public void createRoomsPanel() {

        // Prepare data for tag
        data = Controller_Room.getRoomInfo();
        roomContainer = new JPanel(new BorderLayout());
//        JPanel overviewPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        JPanel overviewPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        overviewPanel.setPreferredSize(new Dimension(0, 188 * Math.ceilDiv(data.length,4)));
        overviewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        overviewPanel.setOpaque(false);

        //Create tags in UI
        for (String[] room : data) {
            overviewPanel.add(generateTagPanel(room[0], room[1], room[2], room[3], room[4]));
        }
        roomContainer.add(overviewPanel,BorderLayout.NORTH);
        roomScrollPane = new JScrollPane(roomContainer);
        roomScrollPane.setPreferredSize(new Dimension(0, 0));
        roomScrollPane.getVerticalScrollBar().setUnitIncrement(12);
        add(roomScrollPane, BorderLayout.CENTER);


    }
    public void createFunctionsPanel(){
        functionContainer = new JPanel(new FlowLayout());
        functionContainer.setPreferredSize(new Dimension(200,0));
        functionContainer.setBorder(new LineBorder(Color.black));

        this.searchBtn = InputComboPanel.generateButton("Search");
        functionContainer.add(generateFunctionInput("Search Room Code", this.searchRoomId));
        functionContainer.add(this.searchBtn);

        add(functionContainer, BorderLayout.EAST);
    }
    public JPanel generateFunctionInput(String strLabel, JTextField originInp){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel.setPreferredSize(new Dimension(180, 65));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }

    public JPanel generateTagPanel(String roomCode,String name, String quantity,String maxQuantity, String price) {
        // Set color for tag
        tag = new JPanel(new BorderLayout());
        if (Integer.parseInt(quantity) > 0 || Integer.parseInt(quantity) == -1) {
            tag.setBackground(Configs.normalGreen);
        } else {
            tag.setBackground(Color.gray);
        }

        //Create and format room panel in tag
        JPanel roomCodePanel = new JPanel();
        roomCodePanel.setBackground(new Color(0,0,0, 40));
        roomCodePanel.setPreferredSize(new Dimension(0,30));
        JLabel roomCodeTag = new JLabel(roomCode);
        roomCodeTag.setFont(roomCodeTag.getFont().deriveFont(20.0F));
        roomCodeTag.setHorizontalAlignment(JLabel.CENTER);
        roomCodeTag.setForeground(Color.white);
        roomCodePanel.add(roomCodeTag);
        tag.add(roomCodePanel, BorderLayout.NORTH);
        JPanel infoPanel = new JPanel(new GridLayout(4, 0));
        infoPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

        // Add information of each room
        infoPanel.add(editLabel(name,16,"name.png"));
        infoPanel.add(editLabel(quantity,16,"quantity.png"));
        infoPanel.add(editLabel(maxQuantity,16,"maxQuantity.png"));
        infoPanel.add(editLabel(Configs.convertStringToVNDCurrency(price),16,"price.png"));
        infoPanel.setOpaque(false);
        tag.add(infoPanel, BorderLayout.CENTER);

        // Create popup menu
        popupMenu = new JPopupMenu();
        JMenuItem contractMenu = new JMenuItem("Contract");
        JMenuItem checkoutMenu = new JMenuItem("Check Out");
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        contractMenu.addActionListener(RoomListeners.contractMenu());
        checkoutMenu.addActionListener(RoomListeners.checkoutMenu());
        updateMenu.addActionListener(RoomListeners.updateMenu(roomCode,quantity,maxQuantity,price,mainFrameApp));
        deleteMenu.addActionListener(RoomListeners.deleteMenu(roomCode,mainFrameApp));
        popupMenu.add(contractMenu);
        popupMenu.add(checkoutMenu);
        popupMenu.add(updateMenu);
        popupMenu.add(deleteMenu);
        tag.setComponentPopupMenu(popupMenu);
        createRoomTagListeners();

        return tag;
    }
    public JLabel editLabel (String text,float fontSize,String img){
        JLabel label = new JLabel(text,new ImageIcon("src/com/motel_management/Assets/img/"+img),JLabel.LEFT);
        label.setForeground(Color.white);
        label.setFont(label.getFont().deriveFont(fontSize));
        return label;
    }
    public void createAddRoomListeners() {
        roomIdInp.setText(RoomListeners.getLastRoomId());

        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomIdInp", this.roomIdInp);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(RoomListeners.addNewRoomListener(inpTags,mainFrameApp));
    }
    public void createFunctionsListeners() {
        this.searchBtn.addActionListener(RoomListeners.searchRoomListener(data,searchRoomId));
    }
    public void createRoomTagListeners() {
        tag.addMouseListener(RoomListeners.addPopupMenu(popupMenu));
    }


}

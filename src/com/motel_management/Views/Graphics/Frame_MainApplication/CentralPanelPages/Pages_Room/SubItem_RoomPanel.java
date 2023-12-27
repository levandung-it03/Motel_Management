package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Invoices.InvoicesOfRoomDialogListeners;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room.RoomListeners;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SubItem_RoomPanel extends JPanel {
    private final Frame_MainApplication mainFrameApp;
    String [] data;
    private final JPopupMenu popupMenu = new JPopupMenu();;
    private final JMenuItem contractMenu = new JMenuItem("Contract");
    private final JMenuItem checkoutMenu = new JMenuItem("Check Out");
    private final JMenuItem updateMenu = new JMenuItem("Update");
    private final JMenuItem deleteMenu = new JMenuItem("Delete");

    public SubItem_RoomPanel(String[] data,Frame_MainApplication mainFrameApp) {
        super(new BorderLayout());
        this.mainFrameApp = mainFrameApp;
        this.data = data;
        this.createRoomTagPanel();
        this.createListeners();
    }
    public void createRoomTagPanel(){
        // Set color for tag
        if (Controller_Room.getRoomStatus(data[0])==0){
            // Room is not occupied
            setBackground(Color.gray);
        } else if (Controller_Room.getRoomStatus(data[0])==1) {
            //Checkout failed due to unpaid payment
            setBackground(new Color(220, 20, 0));
        }else {
            setBackground(Configs.normalGreen);
        }

        //Create and format room panel in tag
        JPanel roomCodePanel = new JPanel();
        roomCodePanel.setBackground(new Color(0,0,0, 40));
        roomCodePanel.setPreferredSize(new Dimension(0,30));
        JLabel roomCodeTag = new JLabel(data[0]);
        roomCodeTag.setFont(roomCodeTag.getFont().deriveFont(20.0f));
        roomCodeTag.setHorizontalAlignment(JLabel.CENTER);
        roomCodeTag.setForeground(Color.white);
        roomCodePanel.add(roomCodeTag);
        add(roomCodePanel, BorderLayout.NORTH);
        JPanel infoPanel = new JPanel(new GridLayout(4, 0));
        infoPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

        // Add information of each room
        infoPanel.add(editLabel(data[1],18,"name.png"));
        if(data[2].equalsIgnoreCase("-1")){
            infoPanel.add(editLabel("Unknown",18,"quantity.png"));
        }else {
            infoPanel.add(editLabel(data[2],18,"quantity.png"));
        }
        infoPanel.add(editLabel(data[3],18,"maxQuantity.png"));
        infoPanel.add(editLabel(Configs.convertStringToVNDCurrency(data[4]),18,"price.png"));
        infoPanel.setOpaque(false);
        add(infoPanel, BorderLayout.CENTER);

        // Create popup menu
        popupMenu.add(contractMenu);
        popupMenu.add(checkoutMenu);
        popupMenu.add(updateMenu);
        popupMenu.add(deleteMenu);
        setComponentPopupMenu(popupMenu);
    }
    public JLabel editLabel (String text,float fontSize,String img){
        JLabel label = new JLabel(text,new ImageIcon("src/com/motel_management/Assets/img/"+img),JLabel.LEFT);
        label.setForeground(Color.white);
        label.setFont(label.getFont().deriveFont(fontSize));
        return label;
    }
    public void createListeners() {
        //Create room tag listener popup menu
        contractMenu.addActionListener(RoomListeners.contractMenu());
        checkoutMenu.addActionListener(RoomListeners.checkoutMenu(data[0],mainFrameApp));
        updateMenu.addActionListener(RoomListeners.updateMenu(data[0],data[2],data[3],data[4],mainFrameApp));
        deleteMenu.addActionListener(RoomListeners.deleteMenu(data[0],mainFrameApp));
        this.addMouseListener(RoomListeners.addPopupMenu(popupMenu));
    }
}

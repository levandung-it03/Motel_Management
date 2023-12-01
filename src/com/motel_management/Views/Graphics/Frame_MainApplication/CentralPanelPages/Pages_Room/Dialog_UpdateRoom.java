package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room.RoomListeners;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Dialog_UpdateRoom extends JDialog{
    JTextField roomId;
    JFrame mainFrameApp;
    JTextField quantity;
    JTextField maxQuantity;
    JTextField defaultPrice;
    JButton updateBtn;

    public Dialog_UpdateRoom(JFrame mainFrameApp, JTextField roomId, JTextField quantity, JTextField maxQuantity, JTextField defaultPrice) throws HeadlessException {
        super(mainFrameApp,"Update");
        this.mainFrameApp = mainFrameApp;
        this.roomId = roomId;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.defaultPrice = defaultPrice;
        createEditDialog();

    }

    public void createEditDialog() {
        JPanel container = new JPanel(new FlowLayout());

        updateBtn = InputComboPanel.generateButton("Update");
        container.add(InputComboPanel.generateTextInputPanel("Quantity (Unknomn = -1)",quantity));
        container.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", defaultPrice));
        container.add(updateBtn);
        add(container);
        createUpdateListener();

        setModal(true);
        setSize(380,350);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void createUpdateListener(){
        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomId", this.roomId);
        inpTags.put("quantity", this.quantity);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.updateBtn.addActionListener(RoomListeners.updateRoom(inpTags,mainFrameApp,this));
    }
}

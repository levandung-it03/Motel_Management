package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_Room.RoomListeners;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EditRoom_Dialog extends JDialog{
    JTextField roomId;
    JTextField quantity;
    JTextField maxQuantity;
    JTextField defaultPrice;
    JButton submitBtn;

    public EditRoom_Dialog(String title, JTextField roomId, JTextField quantity, JTextField maxQuantity, JTextField defaultPrice) throws HeadlessException {
        super();
        this.roomId = roomId;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.defaultPrice = defaultPrice;
        setTitle(title);
        createEditDialog();
        createListener();
    }

    public void createEditDialog() {
        setSize(380,350);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel container = new JPanel(new FlowLayout());

        submitBtn = InputComboPanel.generateButton("Update");
        container.add(InputComboPanel.generateTextInputPanel("Quantity (Unknomn = -1)",quantity));
        container.add(InputComboPanel.generateTextInputPanel("Maximum Quantity", maxQuantity));
        container.add(InputComboPanel.generateTextInputPanel("Default Room Price (VNĐ)", defaultPrice));
        container.add(submitBtn);
        add(container);
    }
    public void createListener(){
        HashMap<String, JTextField> inpTags = new HashMap<>();
        inpTags.put("roomId", this.roomId);
        inpTags.put("quantity", this.quantity);
        inpTags.put("maxQuantity", this.maxQuantity);
        inpTags.put("defaultPrice", this.defaultPrice);

        this.submitBtn.addActionListener(RoomListeners.editRoom(inpTags,this));
    }
}

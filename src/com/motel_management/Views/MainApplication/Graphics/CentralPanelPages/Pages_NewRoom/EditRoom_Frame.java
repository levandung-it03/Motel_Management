package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_NewRoom;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listener_NewRoom.NewRoomListeners;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.AddRoomListeners;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EditRoom_Frame extends JFrame{
    JTextField roomId = new JTextField(20);
    JTextField quantity = new JTextField(20);
    JTextField maxQuantity = new JTextField(20);
    JTextField defaultPrice = new JTextField(20);
    JButton submitBtn;

    public EditRoom_Frame(String title, JTextField roomId, JTextField quantity, JTextField maxQuantity, JTextField defaultPrice) throws HeadlessException {
        super(title);
        this.roomId = roomId;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.defaultPrice = defaultPrice;
        createEditFrame();
        createListener();
    }

    public void createEditFrame() {;
        setSize(380,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
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

        this.submitBtn.addActionListener(NewRoomListeners.editRoom(inpTags,this));
    }
}

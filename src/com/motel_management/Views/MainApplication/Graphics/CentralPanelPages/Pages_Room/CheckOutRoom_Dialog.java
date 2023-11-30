package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_Room;

import com.motel_management.Views.Configs;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Room.RoomListeners;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class CheckOutRoom_Dialog extends JDialog {
    JButton checkOutBtn;
    JDateChooser checkOutDate = new JDateChooser(Date.valueOf(LocalDate.now()));
    JTextArea reason;
    JFrame mainFrameApp;
    String roomId;

    public CheckOutRoom_Dialog(String roomId,JFrame mainFrameApp) {
        super(mainFrameApp,"Check Out");
        this.roomId = roomId;
        this.mainFrameApp = mainFrameApp;
        createCheckOutDialog();
    }
    public void createCheckOutDialog(){
        JPanel container = new JPanel(new FlowLayout());

        checkOutBtn = InputComboPanel.generateButton("Check Out");
        JPanel reasonPanel = new JPanel(new BorderLayout());
        reasonPanel.setBorder(new EmptyBorder(5, 5, 10, 5));
        reasonPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 120));
        JLabel label = new JLabel("Reason (<255 characters)");
        label.setFont(label.getFont().deriveFont(14.0f));
        reason = new JTextArea();
        reason.setLineWrap(true);
        reason.setWrapStyleWord(true);
        reason.setBorder(new LineBorder(Color.gray));
        reason.setPreferredSize(new Dimension(0,100));
        reasonPanel.add(label, BorderLayout.NORTH);
        reasonPanel.add(reason, BorderLayout.CENTER);

        container.add(InputComboPanel.generateDateInputPanel("Check Out Date", checkOutDate));
        container.add(reasonPanel);
        container.add(checkOutBtn);
        add(container);
        createCheckOutListener();

        setModal(true);
        setSize(380,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void createCheckOutListener(){
        checkOutBtn.addActionListener(RoomListeners.checkOutRoom(roomId,checkOutDate,reason,mainFrameApp,this));
    }
}

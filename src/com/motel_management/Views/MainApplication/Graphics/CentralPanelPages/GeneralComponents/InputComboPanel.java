package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents;

import com.motel_management.Views.Configs;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputComboPanel {
    public InputComboPanel() {}

    public static JPanel generateTextInputPanel(String strLabel, JTextField originInp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 65));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel generateDateInputPanel(String strLabel, JDateChooser originInp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 65));

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);
        return panel;
    }

    public static <T> JPanel generateComboBoxInputPanel(String strLabel, JComboBox<T> originInp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth*0.22), 65));

        originInp.setBackground(Configs.mainWhiteBackground);
        originInp.setFont(Configs.labelFont);

        JLabel label = new JLabel(strLabel);
        label.setFont(label.getFont().deriveFont(14.0f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(originInp, BorderLayout.CENTER);

        return panel;
    }

    public static JButton generateButton(String btnName) {
        JButton btn = new JButton(btnName);
        btn.setPreferredSize(Configs.buttonDimension);
        btn.setFont(Configs.labelFont);
        return btn;
    }
}

package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents;

import com.motel_management.Views.Configs;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputComboPanel {
    public InputComboPanel() { super(); }

    public static JPanel generatePasswordInputPanel(String strLabel, JPasswordField originInp, Color color) {
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        passwordPanel.setBackground(color);

        JLabel passwordLabel = new JLabel(strLabel);
        passwordLabel.setFont(Configs.labelFont);
        passwordLabel.setForeground(Configs.greyTextColor);

        JButton changeStatusPasswordBtn = InputComboPanel.generateButton("Show Password");
        changeStatusPasswordBtn.setPreferredSize(new Dimension(115, 10));
        changeStatusPasswordBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        changeStatusPasswordBtn.setForeground(new Color(59, 123, 255));
        changeStatusPasswordBtn.setBackground(color);
        changeStatusPasswordBtn.setFont(Configs.labelFont);

        JPanel passwordLabelsPanel = new JPanel(new BorderLayout());
        passwordLabelsPanel.add(passwordLabel, BorderLayout.WEST);
        passwordLabelsPanel.add(changeStatusPasswordBtn, BorderLayout.EAST);
        passwordLabelsPanel.setBackground(color);

        originInp.setPreferredSize(new Dimension(Configs.loginTagsWidth, Configs.loginTagsHeight));
        originInp.setBorder(new LineBorder(Configs.blackTextColor, 1, true));

        passwordPanel.add(passwordLabelsPanel, BorderLayout.NORTH);
        passwordPanel.add(originInp, BorderLayout.CENTER);

        changeStatusPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (originInp.getEchoChar() != (char) 0) {
                    changeStatusPasswordBtn.setText("Hide Password");
                    originInp.setEchoChar((char) 0);
                } else {
                    changeStatusPasswordBtn.setText("Show Password");
                    originInp.setEchoChar('*');
                }
            }
        });
        return passwordPanel;
    }

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

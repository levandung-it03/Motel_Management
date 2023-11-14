package com.motel_management.Views;

import com.motel_management.Cotrollers.Controller_Login;
import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame_Login extends JFrame {
    int fullWith = 500, fullHeight = 350;
    private final JPanel centralPanel = new JPanel();
    private final JPanel usernamePanel = new JPanel(new BorderLayout());
    private final JPanel passwordPanel = new JPanel(new BorderLayout());
    private final JPanel buttonsPanel = new JPanel(new BorderLayout());

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JButton loginBtn = new JButton("Login");
    private final JButton resetBtn = new JButton("Reset");

    public Frame_Login() {
        super("Motel Management - Login");
    }

    public static void startLoginFrame() {
        Frame_Login mainFrame = new Frame_Login();
        mainFrame.createLoginFrame();
        mainFrame.createListeners();
    }

    public void createLoginFrame() {
        UIManager.put("Label.font", Configuration.labelFont);
        UIManager.put("Label.foreground", Configuration.blackTextColor);

        JLabel title = new JLabel("MOTEL");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        setLayout(new BorderLayout());
        setSize(fullWith, fullHeight);

        // -------------Central Panel-------------
        centralPanel.setPreferredSize(new Dimension(fullWith*3/5, fullHeight));

        add(centralPanel, BorderLayout.CENTER);
        // -------------Title-------------
        title.setFont(title.getFont().deriveFont(38.0f));
        title.setBorder(new EmptyBorder(30, 30, 10, 40));

        // -------------Username (usernamePanel)-------------
        usernameField.setPreferredSize(new Dimension(fullWith*3/5, 35));
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(14.0f));
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        // -------------Password (passwordPanel)-------------
        passwordField.setPreferredSize(new Dimension(fullWith*3/5, 35));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(14.0f));
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        // -------------Buttons (buttonsPanel)-------------
        loginBtn.setPreferredSize(new Dimension(70, 30));
        resetBtn.setPreferredSize(new Dimension(70, 30));
        buttonsPanel.add(loginBtn, BorderLayout.WEST);
        buttonsPanel.add(resetBtn, BorderLayout.EAST);
        buttonsPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        buttonsPanel.setPreferredSize(new Dimension(fullWith/2, 50));

        usernamePanel.setBackground(Configuration.normalGreen);
        passwordPanel.setBackground(Configuration.normalGreen);
        buttonsPanel.setBackground(Configuration.normalGreen);
        centralPanel.setBackground(Configuration.normalGreen);

        centralPanel.add(title);
        centralPanel.add(usernamePanel);
        centralPanel.add(passwordPanel);
        centralPanel.add(buttonsPanel);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createListeners() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = usernameField.getText();
                String p = passwordField.getText();
                String user = Controller_Login.validate(u, p);
                if (user == null) {
                    JOptionPane.showMessageDialog(centralPanel, "Information is not correct!");
                } else {
                    setVisible(false);
                    Frame_MainApplication.startMainApplicationFrame(user);
                }
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }
}

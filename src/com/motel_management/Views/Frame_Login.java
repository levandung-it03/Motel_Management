package com.motel_management.Views;

<<<<<<< HEAD
import  com.motel_management.Controllers.Controller_Login;
=======
import com.motel_management.Controllers.Controller_Login;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;
>>>>>>> 144381b24417423813bf0490bcbe18574049b328

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Frame_Login extends JFrame {
    int fullWith = 500, fullHeight = 350;
    private final JPanel centralPanel = new JPanel();
    private final JPanel usernamePanel = new JPanel(new BorderLayout());
    private final JPanel passwordPanel = new JPanel(new BorderLayout());
    private final JPanel buttonsPanel = new JPanel(new BorderLayout());

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JButton loginBtn = InputComboPanel.generateButton("Login");
    private final JButton resetBtn = InputComboPanel.generateButton("Reset");

    public Frame_Login() {
        super("Motel Management - Login");
    }

    public static void startLoginFrame() {
        Frame_Login mainFrame = new Frame_Login();
        mainFrame.createLoginFrame();
        mainFrame.createOnsiteListeners();
    }

    public void createLoginFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        JLabel title = new JLabel("MOTEL");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        setLayout(new BorderLayout());
        setSize(fullWith, fullHeight);

        // -------------Central Panel-------------
        centralPanel.setPreferredSize(new Dimension(fullWith*3/5, fullHeight));

        add(centralPanel, BorderLayout.CENTER);
        // -------------Title-------------
        title.setFont(title.getFont().deriveFont(42.0f));
        title.setBorder(new EmptyBorder(30, 30, 10, 40));

        // -------------Username (usernamePanel)-------------
        usernameField.setPreferredSize(new Dimension(fullWith*3/5, Configs.inputTagHeight));
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(14.0f));
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        // -------------Password (passwordPanel)-------------
        passwordField.setPreferredSize(new Dimension(fullWith*3/5, Configs.inputTagHeight));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(14.0f));
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        // -------------Buttons (buttonsPanel)-------------
        buttonsPanel.add(loginBtn, BorderLayout.WEST);
        buttonsPanel.add(resetBtn, BorderLayout.EAST);
        buttonsPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        buttonsPanel.setPreferredSize(new Dimension(fullWith/2, 50));

        usernamePanel.setBackground(Configs.normalGreen);
        passwordPanel.setBackground(Configs.normalGreen);
        buttonsPanel.setBackground(Configs.normalGreen);
        centralPanel.setBackground(Configs.normalGreen);

        centralPanel.add(title);
        centralPanel.add(usernamePanel);
        centralPanel.add(passwordPanel);
        centralPanel.add(buttonsPanel);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createOnsiteListeners() {
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

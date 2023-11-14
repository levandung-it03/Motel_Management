package com.motel_management.Views;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    int fullWith = 500, fullHeight = 350;
    private final Color mainBackgroundColor = new Color(74, 161, 67);
    private final Color mainTextColor = new Color(0, 0, 0);
    private final Font labelFont = new Font("Arial", Font.PLAIN, 14);
    private final JPanel centralPanel = new JPanel();
    private final JLabel title = new JLabel("MOTEL");
    private final JPanel usernamePanel = new JPanel(new BorderLayout());
    private final JLabel usernameLabel = new JLabel("Username");
    private final JTextField usernameField = new JTextField();
    private final JPanel passwordPanel = new JPanel(new BorderLayout());
    private final JLabel passwordLabel = new JLabel("Password");
    private final JPasswordField passwordField = new JPasswordField();
    private final JPanel buttonsPanel = new JPanel(new BorderLayout());
    private final JButton loginBtn = new JButton("Login");
    private final JButton resetBtn = new JButton("Reset");

    public LoginFrame() {
        super("Motel Management - Login");
    }

    public static void startLoginFrame() {
        LoginFrame mainFrame = new LoginFrame();
        mainFrame.createLoginFrame();
        mainFrame.createListeners();
    }

    public void createLoginFrame() {
        UIManager.put("Label.font", labelFont);
        UIManager.put("Label.foreground", mainTextColor);

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

        usernamePanel.setBackground(mainBackgroundColor);
        passwordPanel.setBackground(mainBackgroundColor);
        buttonsPanel.setBackground(mainBackgroundColor);
        centralPanel.setBackground(mainBackgroundColor);

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
                ArrayList<Account> a = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + u + "\")");
                if (a.isEmpty()) {
                    JOptionPane.showMessageDialog(centralPanel, "Username is not existed!");
                } else if (!a.get(0).getPassword().equals(p)) {
                    JOptionPane.showMessageDialog(centralPanel, "Password is not correct!");
                } else {
                    JOptionPane.showMessageDialog(centralPanel, "Login successfully!");
                    setVisible(false);
                    startMainApplication(a.get(0).getName());
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

    public void startMainApplication(String user) {
        MainApplicationFrame.startMainApplicationFrame(user);
    }
}

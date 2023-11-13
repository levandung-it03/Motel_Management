package com.motel_management.Views;

import javax.swing.*;
import java.awt.*;

public class LoginFrame {
    public LoginFrame() {}

    public static void createLoginFrame() {
        JFrame loginFrame = new JFrame("Motel Management - Login");
        loginFrame.setLayout(new FlowLayout());
        loginFrame.setSize(600, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(300, 200));

        // Username
        JPanel usernamePanel = new JPanel(new BorderLayout());
        JLabel usernameLabel = new JLabel("Username");
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(200, 100));
        usernamePanel.add(usernameLabel, BorderLayout.WEST);
        usernamePanel.add(username, BorderLayout.EAST);

        // Password
        JPanel passwordPanel = new JPanel(new BorderLayout());
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField();
        password.setPreferredSize(new Dimension(200, 30));
        passwordPanel.add(passwordLabel, BorderLayout.WEST);
        passwordPanel.add(password, BorderLayout.EAST);


        mainPanel.add(usernamePanel, BorderLayout.NORTH);
        mainPanel.add(passwordPanel, BorderLayout.SOUTH);
        loginFrame.add(mainPanel);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

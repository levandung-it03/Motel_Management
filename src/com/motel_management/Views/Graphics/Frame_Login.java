package com.motel_management.Views.Graphics;

import com.motel_management.Configs;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Listeners.Listeners_Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Frame_Login extends JFrame {
    int fullWith = 400, fullHeight = 370;

    private final JPanel centralPanel = new JPanel();
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JButton loginBtn = InputComboPanel.generateButton("Login Now");
    private final JButton changeStatusPasswordBtn = InputComboPanel.generateButton("Show Password");
    private final JButton changePasswordBtn = InputComboPanel.generateButton("<html><u>Change Password</u></html>");

    public Frame_Login() {
        super("Motel Management - Login");
        this.createLoginFrame();
        this.createOnsiteListeners();
    }

    public void createLoginFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setLayout(new BorderLayout());
        setSize(fullWith, fullHeight);
        centralPanel.setPreferredSize(new Dimension(fullWith*9/10, fullHeight));

        // -------------Title Label-------------
        JLabel title = new JLabel("MOTEL");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 42.0f));
        title.setBorder(new EmptyBorder(25, 40, 3, 40));

        // -------------Username (usernamePanel)-------------
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setBorder(new EmptyBorder(10, 0, 15, 0));

        JLabel usernameLabel = new JLabel("Username");
        usernameField.setPreferredSize(new Dimension(Configs.loginTagsWidth, Configs.loginTagsHeight));
        usernameField.setBorder(new LineBorder(Configs.blackTextColor, 1, true));
        usernameField.setFont(Configs.labelFont);
        usernameLabel.setFont(Configs.labelFont);
        usernameLabel.setForeground(Configs.greyTextColor);

        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        // -------------Password (passwordPanel)-------------
        JPanel passwordPanel = InputComboPanel.generatePasswordInputPanel("Password", this.passwordField,
                Configs.mainWhiteBackground);

        // -------------Buttons (buttonsPanel)-------------
        JPanel buttonsPanel = new JPanel(new BorderLayout(10, 0));

        loginBtn.setBorder(new LineBorder(Color.BLACK, 0, true));
        loginBtn.setPreferredSize(new Dimension(Configs.loginTagsWidth/2 - 10, Configs.loginTagsHeight));
        loginBtn.setFont(Configs.labelFont);
        loginBtn.setForeground(Configs.mainWhiteBackground);
        loginBtn.setBackground(Configs.blackTextColor);

        changePasswordBtn.setBorder(new LineBorder(Color.BLACK, 0, true));
        changePasswordBtn.setPreferredSize(new Dimension(Configs.loginTagsWidth/2 - 10, Configs.loginTagsHeight));
        changePasswordBtn.setFont(Configs.labelFont);
        changePasswordBtn.setForeground(Configs.greyTextColor);
        changePasswordBtn.setBackground(Configs.mainWhiteBackground);

        buttonsPanel.add(loginBtn, BorderLayout.WEST);
        buttonsPanel.add(changePasswordBtn, BorderLayout.EAST);

        usernamePanel.setBackground(Configs.mainWhiteBackground);
        buttonsPanel.setBackground(Configs.mainWhiteBackground);
        centralPanel.setBackground(Configs.mainWhiteBackground);
        setBackground(Configs.mainWhiteBackground);

        centralPanel.add(title);
        centralPanel.add(usernamePanel);
        centralPanel.add(passwordPanel);
        centralPanel.add(buttonsPanel);

        add(centralPanel, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createOnsiteListeners() {
        loginBtn.addActionListener(Listeners_Login.loginAction(this));

        changePasswordBtn.addActionListener(Listeners_Login.showChangePasswordFrameAction(this));
    }

    // Getters
    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
}

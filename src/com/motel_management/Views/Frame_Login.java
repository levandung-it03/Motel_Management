package com.motel_management.Views;

import com.motel_management.Controllers.Controller_ChooseRegion;
import com.motel_management.Controllers.Controller_Login;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents.InputComboPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

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
    }

    public static void startLoginFrame() {
        Frame_Login mainFrame = new Frame_Login();
        mainFrame.createLoginFrame();
        mainFrame.createOnsiteListeners();
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
        Frame_Login _this = this;
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
                    String currentRegion = Controller_ChooseRegion.checkIfRegionExisted();
                    if (currentRegion == null) {
                        Frame_ChooseRegion.startChooseRegionFrame(user);
                    } else {
                        Frame_MainApplication.startMainApplicationFrame(user, currentRegion);
                    }
                }
            }
        });

        changeStatusPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getEchoChar() != (char) 0) {
                    changeStatusPasswordBtn.setText("Hide Password");
                    passwordField.setEchoChar((char) 0);
                } else {
                    changeStatusPasswordBtn.setText("Show Password");
                    passwordField.setEchoChar('*');
                }
            }
        });

        changePasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame_ChangePassword.startLoginFrame(_this);
                _this.setVisible(false);
            }
        });

    }
}

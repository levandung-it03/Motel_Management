package com.motel_management.Views.Graphics;

import com.motel_management.Controllers.Controller_ChangePassword;
import com.motel_management.Views.Configs;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

public class Frame_ChangePassword extends JFrame{
    int fullWith = 450, fullHeight = 550;

    private final JPanel centralPanel = new JPanel();
    private final JTextField usernameField = new JTextField();
    private final JPasswordField oldPasswordField = new JPasswordField();
    private final JPasswordField newPasswordField = new JPasswordField();
    private final JPasswordField newPasswordAgainField = new JPasswordField();
    private Frame_Login loginFrame;

    private final JButton submitBtn = InputComboPanel.generateButton("Submit Information");

    public Frame_ChangePassword(String title) {
        super(title);
    }

    public static void startLoginFrame(Frame_Login loginFrame) {
        Frame_ChangePassword mainFrame = new Frame_ChangePassword("Motel Management - Change Password");
        mainFrame.createLoginFrame();
        mainFrame.createOnsiteListeners();
        mainFrame.loginFrame = loginFrame;
    }

    public void createLoginFrame() {
        UIManager.put("Label.font", Configs.labelFont);
        UIManager.put("Label.foreground", Configs.blackTextColor);

        setLayout(new BorderLayout());
        setSize(fullWith, fullHeight);
        centralPanel.setPreferredSize(new Dimension(fullWith*9/10, fullHeight));

        // -------------Title Label-------------
        JLabel title = new JLabel("Password Form");
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
        JPanel oldPasswordPanel = InputComboPanel.generatePasswordInputPanel("Old Password", this.oldPasswordField,
                Configs.mainWhiteBackground);
        JPanel newPasswordField = InputComboPanel.generatePasswordInputPanel("New Password", this.newPasswordField,
                Configs.mainWhiteBackground);
        JPanel newPasswordAgainField = InputComboPanel.generatePasswordInputPanel("New Password Again", this.newPasswordAgainField,
                Configs.mainWhiteBackground);

        // -------------Buttons (buttonsPanel)-------------
        JPanel buttonsPanel = new JPanel(new BorderLayout());

        submitBtn.setBorder(new LineBorder(Color.BLACK, 0, true));
        submitBtn.setPreferredSize(new Dimension(Configs.loginTagsWidth, Configs.loginTagsHeight));
        submitBtn.setFont(Configs.labelFont);
        submitBtn.setForeground(Configs.mainWhiteBackground);
        submitBtn.setBackground(Configs.blackTextColor);

        buttonsPanel.add(submitBtn, BorderLayout.CENTER);

        usernamePanel.setBackground(Configs.mainWhiteBackground);
        buttonsPanel.setBackground(Configs.mainWhiteBackground);
        centralPanel.setBackground(Configs.mainWhiteBackground);
        setBackground(Configs.mainWhiteBackground);

        centralPanel.add(title);
        centralPanel.add(usernamePanel);
        centralPanel.add(oldPasswordPanel);
        centralPanel.add(newPasswordField);
        centralPanel.add(newPasswordAgainField);
        centralPanel.add(buttonsPanel);

        add(centralPanel, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void createOnsiteListeners() {
        Frame_ChangePassword _this = this;
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                _this.loginFrame.setVisible(true);
            }
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String oldPass = oldPasswordField.getText();
                String newPass = newPasswordField.getText();
                String newPassAgain = newPasswordAgainField.getText();

                if (user.isEmpty() || oldPass.isEmpty() || newPass.isEmpty() || newPassAgain.isEmpty()) {
                    JOptionPane.showMessageDialog(new JPanel(), "There Are Several Field Is Empty!");
                    return;
                }

                if (newPass.length() < 6) {
                    JOptionPane.showMessageDialog(new JPanel(), "New Password Is Too Short (length >= 6)!");
                    return;
                }

                if (!newPass.equals(newPassAgain)) {
                    JOptionPane.showMessageDialog(new JPanel(), "Both New Passwords Are Invalid!");
                    return;
                }

                HashMap<String, String> result = Controller_ChangePassword.changePassword(user, oldPass, newPassAgain);

                JOptionPane.showMessageDialog(new JPanel(), result.get("message"), "Notice", JOptionPane.PLAIN_MESSAGE);
                if (result.get("result").equals("1")) {
                    _this.setVisible(false);
                    _this.loginFrame.setVisible(true);
                }
            }
        });

    }
}

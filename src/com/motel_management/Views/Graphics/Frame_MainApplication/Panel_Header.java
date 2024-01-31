package com.motel_management.Views.Graphics.Frame_MainApplication;
import com.motel_management.Configs;
import com.motel_management.Views.Listeners.Listeners_Header;

import javax.swing.*;
import java.awt.*;

public class Panel_Header extends JPanel {
    private final String user;
    private final String currentRegion;
    private final JFrame mainFrameApp;

    private JMenuItem resetItem;
    private JMenuItem logOutItem;

    // Constructor
    public Panel_Header(String currentRegion, String user, JFrame mainFrameApp) {
        super(new BorderLayout());
        this.user = user;
        this.currentRegion = currentRegion;
        this.mainFrameApp = mainFrameApp;

        this.createHeader();
        this.createListeners();
    }

    public void createHeader() {
        int headerWidth = Configs.fullWidth;
        int headerHeight = (int) (Configs.fullHeight / 22);
        setPreferredSize(new Dimension(headerWidth, headerHeight));
        setBackground(Configs.lightGreen);

        JLabel headerLabel = new JLabel("Motel Management - Welcome " + user);
        headerLabel.setFont(headerLabel.getFont().deriveFont(24.0f));
        headerLabel.setForeground(Configs.blackTextColor);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Header Tools
        ImageIcon iconOptions = new ImageIcon("src/com/motel_management/Assets/img/options.png");
        ImageIcon iconLogOut = new ImageIcon("src/com/motel_management/Assets/img/logout.png");
        ImageIcon iconReset = new ImageIcon("src/com/motel_management/Assets/img/reset.png");

        JMenuBar menuToolsContainer = new JMenuBar();
        JMenu menuTools = new JMenu(" OPTIONS ");
        menuTools.setIcon(iconOptions);

        this.resetItem = new JMenuItem("RESET");
        resetItem.setIcon(iconReset);
        menuTools.add(resetItem);

        this.logOutItem = new JMenuItem("LOG OUT");
        logOutItem.setIcon(iconLogOut);
        menuTools.add(logOutItem);

        menuToolsContainer.add(menuTools);

        add(headerLabel, BorderLayout.CENTER);
        add(menuToolsContainer,BorderLayout.EAST);
    }

    public void createListeners() {
        logOutItem.addActionListener(Listeners_Header.logOutAction(mainFrameApp));
        resetItem.addActionListener(Listeners_Header.resetRegionAction(mainFrameApp,currentRegion));
    }
}
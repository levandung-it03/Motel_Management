package com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.GeneralComponents;

import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LabelAsColumnName {
    public LabelAsColumnName() {}

    public static JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Configs.labelFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Configs.blackTextColor);
        label.setPreferredSize(new Dimension(200, 30));
        label.setBorder(new LineBorder(Configs.blackTextColor, 1, false));
        return label;
    }
}

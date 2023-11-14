package com.motel_management.Views.MainApplication.Panel;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Category extends JPanel {
    private final int categoryWidth;
    private final int categoryHeight;

    private final ArrayList<JPanel> categories = new ArrayList<JPanel>();

    // Constructor
    public Category(int fullWidth, int fullHeight) {
        // Add Your Layout Here
        super(new FlowLayout());
        this.categoryWidth = (int) (fullWidth / 7);
        this.categoryHeight = (int) (fullHeight * 10) /11;
        this.createCategory();
    }
    public void createCategory() {
        setBackground(Configuration.darkGreen);
        setPreferredSize(new Dimension(categoryWidth, categoryHeight));
        setBorder(new EmptyBorder(50, 0, 0, 0));


        categories.add(generateCategoryChild("Statistic"));
        categories.add(generateCategoryChild("Representatives"));
        categories.add(generateCategoryChild("Rooms"));
        categories.add(generateCategoryChild("Electricity-Water"));
        categories.add(generateCategoryChild("Consumption"));
        categories.add(generateCategoryChild("Invoices"));

        categories.forEach(this::btn);

    }

    public JPanel generateCategoryChild(String name) {
        JPanel p = new JPanel(new GridLayout());
        p.setBackground(Configuration.lightGreen);
        p.setPreferredSize(new Dimension(categoryWidth, categoryHeight/15));

        JButton b = new JButton(name);
        b.setForeground(Configuration.greenTextColor);
        b.setBackground(Configuration.lightGreen);
        b.setFont(b.getFont().deriveFont(18.0f));

        b.addActionListener()

        p.add(b);
        return p;
    }


}

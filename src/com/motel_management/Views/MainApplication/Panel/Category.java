package com.motel_management.Views.MainApplication.Panel;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import java.awt.*;

public class Category extends JPanel {
    private final int categoryWidth;
    private final int categoryHeight;

    public Category(int fullWidth, int fullHeight) {
        super();
        this.categoryWidth = (int) (fullWidth / 7);
        this.categoryHeight = (int) (fullHeight * 10) /11;
        this.createCategory();
    }
    public void createCategory() {
        setBackground(Configuration.darkGreen);
        setPreferredSize(new Dimension(categoryWidth, categoryHeight));

    }
}

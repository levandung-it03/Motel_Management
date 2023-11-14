package com.motel_management.Views.MainApplication.Panel;
import com.motel_management.Views.Configuration;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private final int footerWidth;
    private final int footerHeight;

    public Footer(int fullWidth, int fullHeight) {
        this.footerWidth = fullWidth;
        this.footerHeight = (int) (fullHeight / 22);
        this.createFooter();
    }

    public void createFooter() {
        setPreferredSize(new Dimension(footerWidth, footerHeight));
        setBackground(Configuration.lightGreen);

    }
}

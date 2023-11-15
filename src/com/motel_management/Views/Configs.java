package com.motel_management.Views;

import java.awt.*;

public class Configs {
    public static final Font labelFont = new Font("Consolas", Font.PLAIN, 16);
    public static final int fullWidth = 1366;
    public static final int fullHeight = 768;
    public static final int centralPanelWidth = (int) (fullWidth * 6) / 7;
    public static final int centralPanelHeight = (int) (fullHeight * 10) /11;

    public static final Color blackTextColor = new Color(33, 33, 33);
    public static final Color greenTextColor = new Color(9, 65, 0);
    public static final Color normalGreen = new Color(74, 161, 67);
    public static final Color lightGreen = new Color(93, 138, 90);
    public static final Color darkGreen = new Color(41, 89, 37);
    public static final Color mainWhiteBackground = new Color(248, 248, 248);

    public static boolean isIntegerNumeric(String str) {
        if (str == null)    return false;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static boolean isNumeric(String str) {
        if (str == null)    return false;
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Configs() {}
}

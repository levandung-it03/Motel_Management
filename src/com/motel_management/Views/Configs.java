package com.motel_management.Views;

import java.awt.*;
import java.time.LocalDate;

public class Configs {
    public static final Font labelFont = new Font("Consolas", Font.PLAIN, 16);
    public static final int fullWidth = 1366;
    public static final int fullHeight = 768;
    public static final int centralPanelWidth = 1082;
    public static final int centralPanelHeight = 610;
    public static final int inputTagHeight = 35;
    public static final Dimension buttonDimension = new Dimension(120, 35);

    public static final Color blackTextColor = new Color(33, 33, 33);
    public static final Color greenTextColor = new Color(9, 65, 0);
    public static final Color normalGreen = new Color(79, 187, 70);
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
    public static LocalDate StringToDate(String str){
        String[] TempStr= str.split("/");
        int Day = Integer.parseInt(TempStr[0]);
        int Month = Integer.parseInt(TempStr[1]);
        int Year = Integer.parseInt(TempStr[2]);
        return LocalDate.of(Year,Month,Day);
    }
}

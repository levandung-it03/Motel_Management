package com.motel_management.Views;

import java.awt.*;
import java.time.LocalDate;

public class Configs {
    public Configs() {}

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

    public static LocalDate stringToDate(String str){
        String[] tempStr= str.split("/");
        if (tempStr.length == 1) {
            tempStr = tempStr[0].split("-");
        }
        int day = Integer.parseInt(tempStr[0]);
        int month = Integer.parseInt(tempStr[1]);
        int year = Integer.parseInt(tempStr[2]);
        return LocalDate.of(year, month, day);
    }

    public static String generateIdTail() {
        StringBuilder milisec = new StringBuilder(Long.toString(System.currentTimeMillis()));
        milisec.replace(0, 4, "");
        return Integer.toString((int) (Math.random()*10)) + milisec.toString();
    }
}

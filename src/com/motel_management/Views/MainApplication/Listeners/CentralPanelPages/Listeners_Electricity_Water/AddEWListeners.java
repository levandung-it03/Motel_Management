package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water;

import com.motel_management.Controllers.Controller_Electricity_Water;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEWListeners {
    public AddEWListeners() {
    }
    public static String getLastElectricId() {
        return Controller_Electricity_Water.getElectricLastId();
    }
    public static String getLastWaterId() {
        return Controller_Electricity_Water.getWaterLastId();
    }
    public static int getLastElectricMaxRange() {
        return Controller_Electricity_Water.getLastElectricMaxRange();
    }
    public static int getLastWaterMaxRange() {
        return Controller_Electricity_Water.getLastWaterMaxRange();
    }

    public static ActionListener addNewELectricListener(HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("E\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("electricId").getText());
                boolean isValid = true;
                if (inpTags.get("maxERangeValue").getText().equalsIgnoreCase("unlimited")){
                    inpTags.get("maxERangeValue").setText(String.valueOf(Integer.MAX_VALUE));
                }
                try {
                    isValid = matcher.matches()
                            && !inpTags.get("rangeName").getText().isEmpty()
                            && Integer.parseInt(inpTags.get("minERangeValue").getText()) > 0
                            && Integer.parseInt(inpTags.get("maxERangeValue").getText()) > Integer.parseInt(inpTags.get("minERangeValue").getText())
                            && Integer.parseInt(inpTags.get("defaultEPrice").getText()) >= 0;
                } catch (NumberFormatException e) { isValid = false; }

                if (isValid) {
                    // Call API here.
                    String nextIdWhenSuccessfully = Controller_Electricity_Water.addNewElectric(new String[] {
                            inpTags.get("electricId").getText(),
                            inpTags.get("rangeName").getText(),
                            inpTags.get("minERangeValue").getText(),
                            inpTags.get("maxERangeValue").getText(),
                            inpTags.get("defaultEPrice").getText()
                    });
                    if (nextIdWhenSuccessfully != null) {
                        JOptionPane.showMessageDialog(new JPanel(), "New electricity range was added! Open \"Electricity_Water List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);

                        inpTags.get("electricId").setText(nextIdWhenSuccessfully);
                        inpTags.get("rangeName").setText("");
                        inpTags.get("minERangeValue").setText(String.valueOf(AddEWListeners.getLastElectricMaxRange()+1));
                        inpTags.get("maxERangeValue").setText("");
                        inpTags.get("defaultEPrice").setText("");
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "Electric Range Already Existed", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
    public static ActionListener addNewWaterListener(HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("W\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("waterId").getText());
                boolean isValid = true;
                if (inpTags.get("maxWRangeValue").getText().equalsIgnoreCase("unlimited")){
                    inpTags.get("maxWRangeValue").setText(String.valueOf(Integer.MAX_VALUE));
                }
                try {
                    isValid = matcher.matches()
                            && !inpTags.get("rangeName").getText().isEmpty()
                            && Integer.parseInt(inpTags.get("minWRangeValue").getText()) > 0
                            && Integer.parseInt(inpTags.get("maxWRangeValue").getText()) > Integer.parseInt(inpTags.get("minWRangeValue").getText())
                            && Integer.parseInt(inpTags.get("defaultWPrice").getText()) >= 0;
                } catch (NumberFormatException e) { isValid = false; }

                if (isValid) {
                    // Call API here.
                    String nextIdWhenSuccessfully = Controller_Electricity_Water.addNewWater(new String[] {
                            inpTags.get("waterId").getText(),
                            inpTags.get("rangeName").getText(),
                            inpTags.get("minWRangeValue").getText(),
                            inpTags.get("maxWRangeValue").getText(),
                            inpTags.get("defaultWPrice").getText()
                    });
                    if (nextIdWhenSuccessfully != null) {
                        JOptionPane.showMessageDialog(new JPanel(), "New water range was added! Open \"Electricity_Water List\" to check it!",
                                "Notice", JOptionPane.PLAIN_MESSAGE);

                        inpTags.get("waterId").setText(nextIdWhenSuccessfully);
                        inpTags.get("rangeName").setText("");
                        inpTags.get("minWRangeValue").setText(String.valueOf(AddEWListeners.getLastWaterMaxRange()+1));
                        inpTags.get("maxWRangeValue").setText("");
                        inpTags.get("defaultWPrice").setText("");
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "Water Range Already Existed", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

    }
}

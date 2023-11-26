package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GeneralListeners {
    public GeneralListeners() { }

    public static String[] getChangedTableRow(TableModelEvent e, TableModelListener listener, JTable table,
                                              Object[][] oldData, String tableName) {
        int changedRowIndex = e.getFirstRow();
        int changedColumnIndex = e.getColumn();
        Object oldCellData = oldData[changedRowIndex][changedColumnIndex];

        if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm this action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            String[] fullChangedRow = new String[table.getColumnCount() - 1];

            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String changedValue = model.getValueAt(changedRowIndex, changedColumnIndex).toString();

            for (int i = 0; i < fullChangedRow.length; i++)
                fullChangedRow[i] = model.getValueAt(changedRowIndex, i).toString();

            boolean isValid = false;

            if (tableName.equals("Room")) {
                isValid = GeneralListeners.validateRoomTableData(oldCellData, changedValue, fullChangedRow);
            }else if (tableName.equals("Electric")) {
                isValid = GeneralListeners.validateEWTableData(oldCellData, changedValue, fullChangedRow);
            }else if (tableName.equals("Water")) {
                isValid = GeneralListeners.validateEWTableData(oldCellData, changedValue, fullChangedRow);
            }

            if (isValid) {
                return fullChangedRow;
            } else {
                // Paying back Old Data.
                table.getModel().removeTableModelListener(listener);
                table.setValueAt(oldCellData, changedRowIndex, changedColumnIndex);
                table.getModel().addTableModelListener(listener);
            }
        }
        else {
            // Paying back Old Data.
            table.getModel().removeTableModelListener(listener);
            table.setValueAt(oldCellData, changedRowIndex, changedColumnIndex);
            table.getModel().addTableModelListener(listener);
        }
        return null;
    }

    public static boolean validateRoomTableData(Object oldCellData, String changedValue, String[] fullChangedRow) {
        if (Configs.isIntegerNumeric(oldCellData.toString()) && !Configs.isIntegerNumeric(changedValue)) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(fullChangedRow[1]) < 0 && Integer.parseInt(fullChangedRow[1]) != -1) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(fullChangedRow[1]) > Integer.parseInt(fullChangedRow[2])) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(fullChangedRow[3]) < 0) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }

        // Occupied Room Being Changed.
        if (ContractDAO.getInstance().selectByCondition("WHERE (roomId =\"" + fullChangedRow[0] + "\" AND checkedOut=\"0\" )").size() > 0) {
            if (Integer.parseInt(fullChangedRow[1]) == 0) {
                JOptionPane.showMessageDialog(new JPanel(), "Max Quantity > 0 because Contract Existed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } else {
            // Not Occupied Room.
            if (Integer.parseInt(fullChangedRow[1]) > 0 || Integer.parseInt(fullChangedRow[1]) == -1) {
                JOptionPane.showMessageDialog(new JPanel(), "Room Was Not Occupied, Can Not Change Quantity!", "Notice", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }
        return true;
    }
    public static boolean validateNewRoomTableData(HashMap<String, JTextField> inpTags) {
        if (!Configs.isIntegerNumeric(inpTags.get("quantity").getText())
                || !Configs.isIntegerNumeric(inpTags.get("maxQuantity").getText())
                || !Configs.isIntegerNumeric(inpTags.get("defaultPrice").getText())) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(inpTags.get("quantity").getText()) < 0 && Integer.parseInt(inpTags.get("quantity").getText()) != -1) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(inpTags.get("quantity").getText()) > Integer.parseInt(inpTags.get("maxQuantity").getText())) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(inpTags.get("defaultPrice").getText()) < 0) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (!ContractDAO.getInstance().selectByCondition("WHERE (roomId =\"" + inpTags.get("roomId").getText() + "\" AND checkedOut=\"0\" )").isEmpty()) {
            if (Integer.parseInt(inpTags.get("quantity").getText()) == 0) {
                JOptionPane.showMessageDialog(new JPanel(), "Max Quantity > 0 because Contract Existed!", "Notice", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } else {
            // Not Occupied Room.
            if (Integer.parseInt(inpTags.get("quantity").getText()) > 0 || Integer.parseInt(inpTags.get("quantity").getText()) == -1) {
                JOptionPane.showMessageDialog(new JPanel(), "Room Was Not Occupied, Can Not Change Quantity!", "Notice", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public static boolean validateEWTableData(Object oldCellData, String changedValue, String[] fullChangedRow) {
        if(fullChangedRow[3].equalsIgnoreCase("unlimited")){
            fullChangedRow[3]= String.valueOf(Integer.MAX_VALUE);
        }
        if (Integer.parseInt(fullChangedRow[2]) < 0) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(fullChangedRow[2]) > Integer.parseInt(fullChangedRow[3])) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (Integer.parseInt(fullChangedRow[4]) < 0) {
            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        return true;
    }

//    public static boolean validateTableData(Object oldCellData, String changedValue, String[] fullChangedRow) {
//        boolean isValid = false;
//        if (!isValid)
//            JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);
//
//        return isValid;
//    }


}

package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralListeners {
    public GeneralListeners() { }

    public static String[] getChangedTableRow(TableModelEvent e, TableModelListener listener, JTable table, Object[][] oldData) {
        int changedRowIndex = e.getFirstRow();
        int changedColumnIndex = e.getColumn();
        Object oldCellData = oldData[changedRowIndex][changedColumnIndex];

        if (JOptionPane.showConfirmDialog(new JPanel(), "Confirm this action?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            String[] fullChangedRow = new String[table.getColumnCount() - 1];

            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String changedValue = model.getValueAt(changedRowIndex, changedColumnIndex).toString();

            for (int i = 0; i < fullChangedRow.length; i++)
                fullChangedRow[i] = model.getValueAt(changedRowIndex, i).toString();

            boolean isValid =
                    (!Configs.isIntegerNumeric(oldCellData.toString()) || Configs.isIntegerNumeric(changedValue))
                            && Integer.parseInt(fullChangedRow[1]) <= Integer.parseInt(fullChangedRow[2])
                            && Integer.parseInt(fullChangedRow[1]) >= 0;

            if (isValid) {
                return fullChangedRow;
            } else {
                JOptionPane.showConfirmDialog(new JPanel(), "Invalid Value", "Notice", JOptionPane.DEFAULT_OPTION);

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

    public static MouseAdapter getCustomDeleteButtonMouseAdapter(DefaultTableModel model, JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // Delete Button Clicked
                if (clickedColumn == table.getColumnCount() - 1) {
                    if (JOptionPane.showConfirmDialog(new Panel(), "Confirm delete this row?", "Confirm",
                            JOptionPane.YES_NO_OPTION) == 0) {

                        if (RoomDAO.getInstance().delete(table.getValueAt(clickedRow, 0).toString()) != 0)
                            JOptionPane.showConfirmDialog(new Panel(), "Delete Successfully!", "Notice", JOptionPane.DEFAULT_OPTION);

                        model.removeRow(clickedRow);
                    }
                }
            }
        };
    }

    public static ActionListener addNewDataByActionListener(HashMap<String, JTextField> inpTags) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Pattern pattern = Pattern.compile("A\\d{3}");
                Matcher matcher = pattern.matcher(inpTags.get("roomCodeInp").getText());
                boolean isValid;

                try {
                    isValid = matcher.matches()
                            && (Integer.parseInt(inpTags.get("quantity").getText()) <= Integer.parseInt(inpTags.get("maxQuantity").getText()))
                            && Configs.isIntegerNumeric(inpTags.get("defaultPrice").getText());
                } catch (NumberFormatException exc) {
                    isValid = false;
                }

                if (isValid) {
                    int res = RoomDAO.getInstance().insert(new String[] {
                            inpTags.get("roomCodeInp").getText(),
                            inpTags.get("quantity").getText(),
                            inpTags.get("maxQuantity").getText(),
                            inpTags.get("defaultPrice").getText()
                    });
                    if (res != 0) {
                        ArrayList<RoomModel> roomList = RoomDAO.getInstance().selectByCondition("ORDER BY roomId ASC");
                        JOptionPane.showMessageDialog(new JPanel(), "Add new Room successfully. See it at \"Room List\"",
                                "Notice", JOptionPane.PLAIN_MESSAGE);

                        StringBuilder lastRoomId = new StringBuilder(roomList.get(roomList.size() - 1).getRoomId());
                        lastRoomId.replace(0, 1, "0");
                        inpTags.get("roomCodeInp").setText("A" + (Integer.parseInt(lastRoomId.toString()) + 1));
                        inpTags.get("quantity").setText("");
                        inpTags.get("maxQuantity").setText("");
                        inpTags.get("defaultPrice").setText("");
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(), "RoomCode Already Existed", "Notice", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid Information", "Notice", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
    }
}

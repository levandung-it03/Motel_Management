package com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Representatives;

import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_RepresentativesPage.Page_RepresentativesMain;
import com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_RepresentativesPage.Dialog_DetailRepresentatives;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.GeneralCentralPanelListeners;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class RepresentativesListeners {
    public static MouseAdapter getInformationByClick(JFrame mainAppFrame, JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // View Button Clicked
                if (clickedColumn == table.getColumnCount() - 1) {
                    PersonModel res =
                            Controller_Representatives.getPersonById(String.valueOf(table.getValueAt(clickedRow,1)));
                    new Dialog_DetailRepresentatives(mainAppFrame, res);
                }
            }
        };
    }

    public static KeyListener searchTableToGetObjects(Page_RepresentativesMain page) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // Make page.tableData Update Continuous.
                GeneralCentralPanelListeners.searchTableToGetObjects(
                        page.getSearchingTextField(),
                        page.getSearchingComboBox(),
                        page.getTable(),
                        page.getTableData(),
                        page.getDefaultModel()
                );
            }
        };
    }

    public static ItemListener getObjectsByYear(Page_RepresentativesMain page) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get Year Input (default =0 with Specified Condition).
                String selectedYear = Objects.requireNonNull(page.getFilterComboBox().getSelectedItem()).toString();
                try { Integer.parseInt(selectedYear);}
                catch (NumberFormatException exc) { selectedYear = "0"; }

                // Call API To Get Table Data.
                Object[][] result = Controller_Representatives.getAllRepresentativesWithTableFormat(selectedYear);

                // Clear Current Table Data.
                page.getDefaultModel().setRowCount(0);

                // Add Row By Row result[][] Into Default Table Model.
                for (Object[] row : result)
                    page.getDefaultModel().addRow(row);

                // Notice To Application That There Are Changes In Our Table.
                page.getDefaultModel().fireTableDataChanged();

                // Save Current Data For Updating, Searching,...
                page.saveCurrentTableData();

                return;
            }
        };
    }
    public static ActionListener updateByClick(HashMap<String, JTextField> inpTags,JTextArea address, JDialog dialog) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String[] data = {inpTags.get("Identifier").getText(), inpTags.get("Email").getText(),
                        inpTags.get("Phone").getText(),
                        inpTags.get("Job-Title").getText(), inpTags.get("Bank").getText(),
                        inpTags.get("BankAccount").getText(),address.getText()};
                String checkConditions = checkUpdatePerson(inpTags,address);
                System.out.println(checkConditions);
                if (checkConditions.equals("true")) {
                    if (Controller_Representatives.updatePersonDetails(data) != 0) {
                        JOptionPane.showMessageDialog(new JPanel() , "Update Successfully!" , "Notice" ,
                                JOptionPane.PLAIN_MESSAGE);
                        dialog.dispose();
                        }
                    } else
                        JOptionPane.showMessageDialog(new JPanel(), "Update Failed! "+ checkConditions, "Notice",
                                JOptionPane.PLAIN_MESSAGE);
                }
        };
    }

    public static String checkUpdatePerson(HashMap<String,JTextField> inpTags,JTextArea address){
        if (inpTags.get("Phone").getText().isBlank()
            || !Pattern.compile("\\d{10}").matcher(inpTags.get("Phone").getText()).matches())
            return "Phone Number Error.";

        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]*)*$").matcher(inpTags.get("Job-Title").getText()).matches()
            || inpTags.get("Job-Title").getText().isBlank())
            return "Job Title Error.";

        if (address.getText().isBlank())
            return "Permanent Address Is Blank.";

        if (!inpTags.get("Email").getText().isBlank()) {
            if (!Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(inpTags.get("Email").getText()).matches()) {
                return "Email Error.";
            }
        } else return "Email Error.";

        if (!inpTags.get("BankAccount").getText().equals("")) {
            if (!inpTags.get("Bank").getText().equals("")){
                if (!Pattern.compile("^[0-9]{1,13}$").matcher(inpTags.get("BankAccount").getText()).matches()) {
                    return "Bank Account Number Error.";
                }
            } else {
                return "empty Bank Name.";
            }
        }

        if (!inpTags.get("Bank").getText().isBlank()) {
            if (inpTags.get("BankAccount").getText().isBlank())
                return "empty Bank Account Number.";
        }

        return "true";
    }
}
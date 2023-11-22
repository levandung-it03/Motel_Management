package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Presentatives;

import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.PersonModel;
import com.motel_management.Controllers.Controller_Representatives;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.RepresentativesListPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PresentativesListeners {
    public static MouseAdapter getInformationByClick(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedRow = table.rowAtPoint(e.getPoint());
                int clickedColumn = table.columnAtPoint(e.getPoint());

                // Delete Button Clicked
                if (clickedColumn == table.getColumnCount() - 1) {
                    PersonModel res = PersonDAO.getInstance().selectById(String.valueOf(table.getValueAt(clickedRow,0)));
                    Controller_Representatives.showInformationById(res);
                }
            }
        };
    }
}

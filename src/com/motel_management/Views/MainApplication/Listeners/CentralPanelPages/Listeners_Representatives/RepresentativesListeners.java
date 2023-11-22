package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Representatives;

import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.PersonModel;
import com.motel_management.Controllers.Controller_Representatives;
<<<<<<< HEAD:src/com/motel_management/Views/MainApplication/Listeners/CentralPanelPages/Listeners_Presentatives/PresentativesListeners.java
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Pages_RepresentativesPage.Representatives_ShowID;
=======
>>>>>>> 5377781705a99750e84ae4c8394b0d8477f8dab1:src/com/motel_management/Views/MainApplication/Listeners/CentralPanelPages/Listeners_Representatives/RepresentativesListeners.java

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RepresentativesListeners {
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
                    new Representatives_ShowID(res);
                }
            }
        };
    }
}

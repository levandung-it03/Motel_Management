package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.Listeners_Electricity_Water;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.Electricity_WaterPage.Electricity_WaterListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class EWListListeners {
    static TableModelListener tmListener;

    // Constructor
    public EWListListeners() {}

    public static TableModelListener cellValueUpdated(Electricity_WaterListPage EWList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    String[] res = GeneralListeners.getChangedTableRow(e, tmListener, EWList.table, EWList.tableData);

                    if (res != null) {
                        System.out.println("Value Updated");
                        RoomDAO.getInstance().update(res);
                    }
                }
                EWList.saveCurrentTableData();
            }
        };
        return tmListener;
    }

}

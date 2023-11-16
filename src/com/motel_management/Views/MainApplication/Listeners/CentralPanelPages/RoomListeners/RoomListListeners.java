package com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.RoomListeners;

import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Views.MainApplication.Graphics.CentralPanelPages.RoomPages.RoomListPage;
import com.motel_management.Views.MainApplication.Listeners.CentralPanelPages.GeneralListeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class RoomListListeners {
    static TableModelListener tmListener;

    // Constructor
    public RoomListListeners() {}

    public static TableModelListener cellValueUpdated(RoomListPage roomList) {
        tmListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    String[] res = GeneralListeners.getChangedTableRow(e, tmListener, roomList.table, roomList.tableData);

                    if (res != null) {
                        System.out.println("Value Updated");
                        RoomDAO.getInstance().update(res);
                    }
                }
                roomList.saveCurrentTableData();
            }
        };
        return tmListener;
    }

}



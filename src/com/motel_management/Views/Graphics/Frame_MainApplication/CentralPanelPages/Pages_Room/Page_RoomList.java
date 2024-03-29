
package com.motel_management.Views.Graphics.Frame_MainApplication.CentralPanelPages.Pages_Room;

import com.motel_management.Controllers.Controller_Room;
import com.motel_management.Configs;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;
import com.motel_management.Views.Graphics.GeneralComponents.InputComboPanel;
import com.motel_management.Views.Listeners.Listeners_CentralPanelPages.Listeners_Room.RoomListeners;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Page_RoomList extends JPanel {
    public JScrollPane roomScrollPane;
    private final Frame_MainApplication mainFrameApp;
    JPanel roomContainer;
    JPanel toolContainer;
    public JComboBox<String> filterComboBox;
    public JTextField searchingTextField = new JTextField();
    public JComboBox<String> searchingComboBox;
    String[][] data;
    public String[] condition;

    public Page_RoomList(Frame_MainApplication mainFrameApp) {
        super(new BorderLayout());
        this.mainFrameApp = mainFrameApp;
        // condition[0]: filter, condition[1]: search column, condition[2]: search text
        this.condition = new String[]{"", "", ""};
        this.createFunctionsPanel();
        this.createRoomsPanel();
        this.createListeners();
    }

    public void createRoomsPanel() {

        // Prepare data for tag
        data = Controller_Room.getRoomInfo(condition);
        roomContainer = new JPanel(new BorderLayout());
        JPanel overviewPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        overviewPanel.setPreferredSize(new Dimension(0, 180 * Math.ceilDiv(data.length, 5)));
        overviewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        overviewPanel.setOpaque(false);

        //Create tags in UI
        for (String[] room : data) {
            overviewPanel.add(new SubItem_RoomPanel(room, mainFrameApp));
        }

        roomContainer.add(overviewPanel, BorderLayout.NORTH);
        roomScrollPane = new JScrollPane(roomContainer);
        roomScrollPane.setPreferredSize(new Dimension(0, 0));
        roomScrollPane.getVerticalScrollBar().setUnitIncrement(12);
        add(roomScrollPane, BorderLayout.CENTER);
    }

    public void createFunctionsPanel() {
        toolContainer = new JPanel(new BorderLayout());
        toolContainer.setPreferredSize(new Dimension(0, 76));
        toolContainer.setBorder(new EmptyBorder(5, 10, 5, 10));

        // Search
        this.searchingComboBox = new JComboBox<>(new String[]{
                "Room",
                "Full Name",
                "Quantity",
                "Max Quantity",
                "Room Price"
        });
        JPanel searchingComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Choose Searched Field", this.searchingComboBox);
        JPanel searchingTextFieldPanel =
                InputComboPanel.generateTextInputPanel("Searching", this.searchingTextField);
        searchingTextFieldPanel.setPreferredSize(new Dimension((int) (Configs.centralPanelWidth * 0.2), 65));

        JPanel searchingContainer = new JPanel(new BorderLayout());
        searchingContainer.add(searchingComboBoxContainer, BorderLayout.WEST);
        searchingContainer.add(searchingTextFieldPanel, BorderLayout.CENTER);
        toolContainer.add(searchingContainer, BorderLayout.WEST);

        // Filter
        this.filterComboBox = new JComboBox<>(new String[]{
                "All Room",
                "Empty Room",
                "Unpaid Room"
        });
        JPanel filterComboBoxContainer =
                InputComboPanel.generateComboBoxInputPanel("Filter Rooms", this.filterComboBox);
        toolContainer.add(filterComboBoxContainer, BorderLayout.EAST);
        add(toolContainer, BorderLayout.NORTH);
    }

    public void createListeners() {

        //Create filter listener
        this.filterComboBox.addItemListener(RoomListeners.getRoomByStatus(this));

        //Create search listener
        searchingTextField.addKeyListener(RoomListeners.searchRoomListener(this));
    }


}

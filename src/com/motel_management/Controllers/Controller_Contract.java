package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Controller_Contract {
    public Controller_Contract() { super(); }
    public static int addNewContract(HashMap<String, String> data) {
        String contractId = "C" + Configs.generateIdTail();
        int totalMonths = Configs.calTotalMonthsBetweenStrDates(data.get("startingDate"), data.get("endingDate"));

        String[] contractData = new String[] {
                contractId,
                data.get("identifier"),
                data.get("roomId"),
                data.get("quantity"),
                data.get("roomDeposit"),
                data.get("isFamily"),
                data.get("startingDate"),
                data.get("endingDate"),
                Integer.toString(totalMonths),
                data.get("isRegisteredPerAddress"),
        };
        
        String[] personData = new String[] {
                data.get("identifier"),
                data.get("roomId"),
                data.get("lastName"),
                data.get("firstname"),
                data.get("birthday"),
                data.get("phone"),
                data.get("gender"),
                data.get("jobTitle"),
                data.get("permanentAddress"),
                data.get("email"),
                data.get("bankAccountNumber"),
                data.get("bank"),
        };

        RoomModel roomData = RoomDAO.getInstance().selectById(data.get("roomId"));
        roomData.setQuantity(Integer.parseInt(data.get("quantity")));

        int addContractRes = ContractDAO.getInstance().insert(contractData);
        int addPersonRes = PersonDAO.getInstance().insert(personData);
        int updateRoomRes = RoomDAO.getInstance().update(roomData);
        return addContractRes * addPersonRes * updateRoomRes;
    }

    public static int updateContract(String[] data) {
        return RoomDAO.getInstance().update(data);
    }

    public static int deleteById(String id, String identifier, String roomId) {
        RoomModel roomData = RoomDAO.getInstance().selectById(roomId);
        roomData.setQuantity(0);

        int deleteContractRes = ContractDAO.getInstance().delete(id);
        int deletePersonRes = PersonDAO.getInstance().delete(identifier);
        int updateRoomRes = RoomDAO.getInstance().update(roomData);
        return deleteContractRes * deletePersonRes * updateRoomRes;
    }

    public static String[][] getAllContractWithTableFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<ContractModel> selectedContracts = ContractDAO.getInstance().selectAll();
        HashMap<String, String> selectedPersons = PersonDAO.getInstance().selectALlNameById();

        String[][] contracts = new String[selectedContracts.size()][8];
        for (int i = 0; i < selectedContracts.size(); i++) {
            contracts[i][0] = selectedContracts.get(i).getRoomId();
            contracts[i][1] = selectedContracts.get(i).getIdentifier();
            contracts[i][2] = selectedPersons.get(selectedContracts.get(i).getIdentifier());
            contracts[i][3] = Integer.toString(selectedContracts.get(i).getRoomDeposit());
            contracts[i][4] = selectedContracts.get(i).getIsRegisteredPerAddress().equals("1") ? "YES" : "NO";
            contracts[i][5] = dateFormat.format(selectedContracts.get(i).getStartingDate());
            contracts[i][6] = dateFormat.format(selectedContracts.get(i).getEndingDate());
            contracts[i][7] = "Delete";
        }
        return contracts;
    }
}

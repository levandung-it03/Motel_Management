package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.RoomModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller_Contract {
    public Controller_Contract() {}

    public static int addNewContract(HashMap<String, String> data) {
        ArrayList<String> preparedContractData = new ArrayList<>();
        String[] contractData = new String[] {
                "000",
                data.get("identifier"),
                data.get("roomId"),
                data.get("quantity"),
                data.get("roomDeposit"),
                data.get("startingDate"),
                data.get("endingDate")
        };
        
        String[] personData = new String[] {
                data.get("identifier"),
                data.get("lastName"),
                data.get("firstname"),
                data.get("birthday"),
                data.get("phone"),
                data.get("gender"),
                data.get("jobTitle"),
                data.get("permanentAddress"),
                data.get("email"),
                data.get("creditCard"),
                data.get("bank"),
                data.get("roomId")
        };

        RoomModel roomData = RoomDAO.getInstance().selectById(data.get("roomId"));
        roomData.setQuantity(Integer.parseInt(data.get("quantity")));

        int addContractRes = ContractDAO.getInstance().insert(contractData);
        int addPersonRes = PersonDAO.getInstance().insert(personData);
        int updateRoomRes = RoomDAO.getInstance().update(roomData);
        return addContractRes * addPersonRes * updateRoomRes;
    }
}

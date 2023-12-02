package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.CheckOutDAO;
import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.DataAccessObject.RoomDAO;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Views.Configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller_Contract {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public Controller_Contract() { super(); }

    public static HashMap<String, String> addNewContract(HashMap<String, String> data) {
        HashMap<String, String> result = new HashMap<>();


        // Check If This Room Already Had 'StartingDate' < 'CheckOutDate'.
        try {
            String lastCheckedOutDateStrOfRoom =
                    CheckOutDAO.getInstance().selectLastCheckedOutDateByRoomId(data.get("roomId"));
            Date lastCheckedOutDateOfRoom = sdf.parse(lastCheckedOutDateStrOfRoom);
            if (sdf.parse(data.get("startingDate")).before(lastCheckedOutDateOfRoom)) {
                result.put("result", "0");
                result.put("message", "Invalid Started Date Because The Last Check-out Date Of This Room Is: "
                        + sdf.format(lastCheckedOutDateOfRoom));
                return result;
            }
        } catch (NullPointerException | ParseException ignored) {}

        // Check If This Person Already Occupied In Another Room, but the 'StartingDate' < 'LastCheckOutDateInAnotherRoom'
        try {
            String[] lastCheckedOutDateStrOfPerson =
                    CheckOutDAO.getInstance().selectLastCheckedOutDateByIdentifier(data.get("identifier"));

            Date lastCheckedOutDateOfPerson = sdf.parse(lastCheckedOutDateStrOfPerson[1]);
            if (sdf.parse(data.get("startingDate")).before(lastCheckedOutDateOfPerson)) {
                result.put("result", "0");
                result.put("message", "Invalid Started Date Because This Person Occupied In"
                        + " Room " + lastCheckedOutDateStrOfPerson[0]
                        + " And Checked-out In: " + sdf.format(lastCheckedOutDateOfPerson));
                return result;
            }
        } catch(NullPointerException | ParseException ignored) {}

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
                "0"
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
                "1"
        };

        if (ContractDAO.getInstance().selectByCondition("WHERE checkedOut=\"0\" AND identifier=\"" + data.get("identifier") + "\"").size() > 0) {
            result.put("result", "0");
            result.put("message", "This Person Is In Another Room!");
            return result;
        }

        int addPersonRes = 0;
        if (PersonDAO.getInstance().selectById(data.get("identifier")) != null) {
            // Person existed but there is no Contract has this Person which hasn't checked out yet.
            addPersonRes = PersonDAO.getInstance().update(personData);
        } else
            addPersonRes = PersonDAO.getInstance().insert(personData);

        int addContractRes = ContractDAO.getInstance().insert(contractData);

        RoomModel roomData = RoomDAO.getInstance().selectById(data.get("roomId"));
        roomData.setQuantity(Integer.parseInt(data.get("quantity")));
        int updateRoomRes = RoomDAO.getInstance().update(roomData);

        if (addContractRes * addPersonRes * updateRoomRes != 0) {
            result.put("result", "1");
            result.put("message", "New Contract was added!");
        } else {
            result.put("result", "0");
            result.put("message", "Something Wrong!");
        }
        return result;
    }

    public static void updateContractStatus(String[] data) {
        ContractDAO.getInstance().updateContractStatus(data);
    }

    public static ContractModel getContractById(String contractId) {
        return ContractDAO.getInstance().selectById(contractId);
    }

    public static int deleteById(String contractId, String roomId, String identifier) {
        RoomModel roomData = RoomDAO.getInstance().selectById(roomId);
        roomData.setQuantity(0);

        int deleteContractRes = ContractDAO.getInstance().delete(contractId);
        // Ignore delete Person Result because there can be several Contracts, which has this Identifier.
        // ==> Just Delete Contract
        PersonDAO.getInstance().delete(identifier);
        int updateRoomRes = RoomDAO.getInstance().update(roomData);
        return deleteContractRes * updateRoomRes;
    }

    public static String[][] getAllContractByYearWithTableFormat(String year) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String query = (
                year.equals("0")
                ? "WHERE checkedOut=\"0\""
                : "WHERE YEAR(startingDate)=\"" + year + "\""
        ) + "ORDER BY roomId ASC, checkedOut ASC";

        ArrayList<ContractModel> selectedContracts = ContractDAO.getInstance()
                .selectByCondition(query);
        HashMap<String, String> selectedPersons = PersonDAO.getInstance().selectAllNameById();

        String[][] contracts = new String[selectedContracts.size()][9];
        for (int i = 0; i < selectedContracts.size(); i++) {
            contracts[i][0] = selectedContracts.get(i).getContractId();
            contracts[i][1] = selectedContracts.get(i).getRoomId();
            contracts[i][2] = selectedContracts.get(i).getIdentifier();
            contracts[i][3] = selectedPersons.get(selectedContracts.get(i).getIdentifier());
            contracts[i][4] = selectedContracts.get(i).getCheckedOut().equals("0") ? "NO" : "YES";
            contracts[i][5] = dateFormat.format(selectedContracts.get(i).getStartingDate());
            contracts[i][6] = dateFormat.format(selectedContracts.get(i).getEndingDate());
            contracts[i][7] = "View";
            contracts[i][8] = "Delete";
        }
        return contracts;
    }
}

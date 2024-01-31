package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.*;
import com.motel_management.Models.ContractModel;
import com.motel_management.Models.PersonModel;
import com.motel_management.Models.RoomModel;
import com.motel_management.Configs;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Controller_Contract {
    public Controller_Contract() { super(); }

    public static HashMap<String, String> addNewContract(HashMap<String, String> data) {
        HashMap<String, String> result = new HashMap<>();

        // Check If This Room Already Had 'StartingDate' < 'CheckOutDate'.
        try {
            String lastCheckedOutDateStrOfRoom =
                    CheckOutDAO.getInstance().selectLastCheckedOutDateByRoomId(data.get("roomId"));
            Date lastCheckedOutDateOfRoom = Configs.simpleDateFormat.parse(lastCheckedOutDateStrOfRoom);
            if (Configs.simpleDateFormat.parse(data.get("startingDate")).before(lastCheckedOutDateOfRoom)) {
                result.put("result", "0");
                result.put("message", "Invalid Started Date Because The Last Checkout Date Of This Room Is: "
                        + Configs.simpleDateFormat.format(lastCheckedOutDateOfRoom));
                return result;
            }
        } catch (NullPointerException | ParseException ignored) {}

        // Check If This Person Already Occupied In Another Room, but the 'StartingDate' < 'LastCheckOutDateInAnotherRoom'
        try {
            String[] lastCheckedOutDateStrOfPerson =
                    CheckOutDAO.getInstance().selectLastCheckedOutDateByIdentifier(data.get("identifier"));
            Date lastCheckedOutDateOfPerson = Configs.simpleDateFormat.parse(lastCheckedOutDateStrOfPerson[1]);

            if (Configs.simpleDateFormat.parse(data.get("startingDate")).before(lastCheckedOutDateOfPerson)) {
                result.put("result", "0");
                result.put("message", "Invalid Started Date Because This Person Occupied In"
                        + " Room " + lastCheckedOutDateStrOfPerson[0]
                        + " And Checked-out In: " + Configs.simpleDateFormat.format(lastCheckedOutDateOfPerson));
                return result;
            }
        } catch(NullPointerException | ParseException ignored) {}

        // Check If This Person Is In Another Room (Have A Contract With isOccupied == 0)
        if (!ContractDAO.getInstance()
                .selectByCondition("WHERE checkedOut=\"0\" AND identifier=\"" + data.get("identifier") + "\"")
                .isEmpty()) {
            result.put("result", "0");
            result.put("message", "This Person Is In Another Room!");
            return result;
        }

        // Check If There's A Contract Added In The Last 24h.
        try {
            ContractModel foundLastContract = ContractDAO.getInstance().selectLastContractByRoomId(data.get("roomId"));
            if (Period.between(foundLastContract.getCreatingTime().toLocalDateTime().toLocalDate(),
            LocalDate.now()).getDays() < 1) {
                result.put("result", "0");
                result.put("message", "There was a Contract added in the last 24h. Please wait to add new Contract!");
                return result;
            } else {
                // Clear the last updated Person history to save new (if there are the old Person with new info).
                PersonTempHistoryDAO.getInstance().delete(data.get("identifier"));
            }
        } catch (NullPointerException ignored) {}

        String contractId = "C" + Configs.generateIdTail();
        String[] contractData = new String[] {
                contractId,
                data.get("identifier"),
                data.get("roomId"),
                data.get("quantity"),
                data.get("roomDeposit"),
                data.get("isFamily"),
                data.get("startingDate"),
                data.get("endingDate"),
                data.get("isRegisteredPerAddress"),
                // CheckedOut
                "0",
                // CreatingTime
                Timestamp.valueOf(LocalDateTime.now()).toString()
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
                data.get("bankAccountNumber"),
                data.get("bank"),
                // IsOccupied
                "1"
        };

        int addPersonRes = 0;
        PersonModel foundPerson = PersonDAO.getInstance().selectById(data.get("identifier"));
        if (foundPerson == null) {
            addPersonRes = PersonDAO.getInstance().insert(personData);
        } else {
            // Person existed but there is no Contract has this Person which hasn't checked out yet.
            addPersonRes = PersonTempHistoryDAO.getInstance().insert(foundPerson);
            addPersonRes *= PersonDAO.getInstance().update(personData);
        }

        int addContractRes = ContractDAO.getInstance().insert(contractData);
        if (addContractRes == -1) {
            result.put("result", "0");
            result.put("message", "This Person is in this room at starting-date: " + data.get("startingDate"));
            return result;
        }

        RoomModel roomData = RoomDAO.getInstance().selectById(data.get("roomId"));
        roomData.setQuantity(Integer.parseInt(data.get("quantity")));
        int updateRoomRes = RoomDAO.getInstance().update(roomData);
//        System.out.println(addPersonRes+" "+updateRoomRes+" "+addContractRes);

        if (addPersonRes * updateRoomRes * addContractRes != 0) {
            result.put("result", "1");
            result.put("message", "Adding Successfully! Please check it because it'll be locked and can't be deleted in 24h!");
        } else {
            result.put("result", "0");
            result.put("message", "Something Wrong!");
        }
        return result;
    }

    public static int updateContractStatus(String[] data) {
        return ContractDAO.getInstance().updateContractStatus(data);
    }

    public static ContractModel getContractById(String contractId) {
        return ContractDAO.getInstance().selectById(contractId);
    }

    public static HashMap<String, String> deleteById(String contractId, String roomId, String identifier) {
        ContractModel currentContract = ContractDAO.getInstance().selectById(contractId);
        HashMap<String, String> result = new HashMap<>();

        if (Period.between(currentContract.getCreatingTime().toLocalDateTime().toLocalDate(),
                LocalDateTime.now().toLocalDate()).getDays() >= 1) {
            result.put("result", "0");
            result.put("message", "Can not delete this Contract! Because it's locked after 24h you had created it!");
            return result;
        }

        int deleteContractRes = ContractDAO.getInstance().delete(contractId);

        /*
        - Perhaps, there are several Contracts, which has this Identifier:
            + The old one: throw exception ==> Stop delete Person, rollback Person Info, but still delete Contract.
            + The new one under 24h: keep deleting.
        */
        int rollbackPersonRes = 0;
        if (PersonDAO.getInstance().delete(identifier) == 0)
            rollbackPersonRes = PersonDAO.getInstance().update(PersonTempHistoryDAO.getInstance().selectById(identifier));

        RoomModel roomData = RoomDAO.getInstance().selectById(roomId);
        roomData.setQuantity(0);
        int updateRoomRes = RoomDAO.getInstance().update(roomData);

        if (rollbackPersonRes * deleteContractRes * updateRoomRes == 0) {
            result.put("result", "0");
            result.put("message", "There's something wrong with your Database!");
        } else {
            result.put("result", "1");
            result.put("message", "Delete Successfully!");
        }
        return result;
    }

    public static String[][] getAllContractByYearWithTableFormat(String year) {
        String conditionQuery = (
                year.equals("0")
                        ? "WHERE checkedOut=0\n"
                        : "WHERE YEAR(startingDate)=\"" + year + "\"\n"
        );
        ArrayList<HashMap<String, String>> selectedContracts = ContractDAO.getInstance()
                .selectAllPersonWithContractTableFormat(conditionQuery);

        if (selectedContracts == null)
            return new String[0][9];

        String[][] contracts = new String[selectedContracts.size()][9];
        for (int i = 0; i < selectedContracts.size(); i++) {
            contracts[i][0] = selectedContracts.get(i).get("contractId");
            contracts[i][1] = selectedContracts.get(i).get("roomId");
            contracts[i][2] = selectedContracts.get(i).get("identifier");
            contracts[i][3] = selectedContracts.get(i).get("fullName");
            contracts[i][4] = Boolean.parseBoolean(selectedContracts.get(i).get("checkedOut")) ? "YES" : "NO";
            contracts[i][5] = selectedContracts.get(i).get("startingDate");
            contracts[i][6] = selectedContracts.get(i).get("endingDate");
            contracts[i][7] = "View";
            contracts[i][8] = "Delete";
        }
        return contracts;
    }
}
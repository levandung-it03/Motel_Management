package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.ContractDAO;
import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.PersonModel;

import java.util.ArrayList;

public class Controller_Representatives {
    public Controller_Representatives() {
        super();
    }

    public static PersonModel getPersonById(String identifier) {
        return PersonDAO.getInstance().selectById(identifier);
    }

    public static void updatePersonStatus(String[] data) {
        PersonDAO.getInstance().updatePersonStatus(data);
    }

    public static String[][] getAllRepresentativesWithTableFormat(String year) {
        String[][] result;

        // Occupying Persons.
        if (year.equals("0")) {
            ArrayList<PersonModel> persons = PersonDAO.getInstance().selectByCondition("WHERE isOccupied=\"1\"");
            result = new String[persons.size()][8];
            for (int i = 0; i < persons.size(); i++) {
                result[i][0] = persons.get(i).getRoomId();
                result[i][1] = persons.get(i).getIdentifier();
                result[i][2] = persons.get(i).getLastName();
                result[i][3] = persons.get(i).getFirstName();
                result[i][4] = persons.get(i).getGender().equals("0") ? "NAM" : "NU";
                result[i][5] = persons.get(i).getJobTitle();
                result[i][6] = persons.get(i).getPhone();
                result[i][7] = "View";
            }
        }
        // Persons Check-in In Year.
        else {
            ArrayList<String> simpleContracts = ContractDAO.getInstance()
                    .selectContractWithRepresentativeByYear("WHERE YEAR(startingDate)=\"" + year + "\"");
            result = new String[simpleContracts.size()][8];
            for (int i = 0; i < simpleContracts.size(); i++) {
                PersonModel person = PersonDAO.getInstance()
                        .selectByCondition("WHERE identifier=\"" + simpleContracts.get(i) + "\"").get(0);
                if (person != null) {
                    result[i][0] = person.getRoomId();
                    result[i][1] = person.getIdentifier();
                    result[i][2] = person.getLastName();
                    result[i][3] = person.getFirstName();
                    result[i][4] = person.getGender().equals("0") ? "NAM" : "NU";
                    result[i][5] = person.getJobTitle();
                    result[i][6] = person.getPhone();
                    result[i][7] = "View";
                }
            }
        }
        return result;
    }
}

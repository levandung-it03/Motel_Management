package com.motel_management.Controllers;

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

    public static int updatePersonStatus(String[] data) {
        return PersonDAO.getInstance().updatePersonStatus(data);
    }

    public static int updatePersonDetails (String[] data) {
        return PersonDAO.getInstance().updateDetails(data);
    }

    public static String[][] getAllRepresentativesWithTableFormat(String year) {
        String[][] result;
        String condition;

        if (year.equals("0"))   condition = "WHERE checkedOut=\"0\"";
        else    condition = "WHERE YEAR(startingDate)=\"" + year +"\"";

        ArrayList<String[]> persons = PersonDAO.getInstance().selectByInnerJoinContract(condition);
        if (persons == null)
            return new String[0][8];
        result = new String[persons.size()][8];

        for (int i = 0; i < persons.size(); i++) {
            result[i][0] = persons.get(i)[0];
            result[i][1] = persons.get(i)[1];
            result[i][2] = persons.get(i)[2];
            result[i][3] = persons.get(i)[3];
            result[i][4] = persons.get(i)[4];
            result[i][5] = persons.get(i)[5];
            result[i][6] = persons.get(i)[6];
            result[i][7] = "View";
        }

        return result;
    }
}

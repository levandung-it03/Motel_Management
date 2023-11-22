package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.PersonDAO;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;
import com.mysql.cj.x.protobuf.MysqlxExpr;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Controller_Representatives {
    public Controller_Representatives(){
        super();
    }

    public static PersonModel getPersonById(String identifier) {
        return PersonDAO.getInstance().selectById(identifier);
    }

    public static String[][] getAllRepresentativesWithTableFormat() {
        ArrayList<PersonModel> result = PersonDAO.getInstance().selectByCondition("Where isOccupied='1'");
        String[][] person = new String[result.size()][6];
        for (int i = 0; i < result.size(); i++) {
            person[i][0] = result.get(i).getIdentifier();
            person[i][1] = result.get(i).getFirstName();
            person[i][2] = result.get(i).getRoomId();
            person[i][3] = result.get(i).getPhone();
            person[i][4] = result.get(i).getPermanentAddress();
            person[i][5] = "Details";
        }
        return person;
    }
}

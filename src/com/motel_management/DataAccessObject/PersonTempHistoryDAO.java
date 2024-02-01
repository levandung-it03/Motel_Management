package com.motel_management.DataAccessObject;

public class PersonTempHistoryDAO extends PersonStereoTypeDAO {

    // Constructor
    public PersonTempHistoryDAO() { super("Person_Temp_History"); }

    public static PersonTempHistoryDAO getInstance() { return new PersonTempHistoryDAO(); }

}

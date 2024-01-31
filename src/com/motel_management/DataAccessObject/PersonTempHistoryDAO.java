package com.motel_management.DataAccessObject;

public class PersonTempHistoryDAO extends PersonStereoTypeDAO {

    // Constructor
    public PersonTempHistoryDAO() { super("PersonTempHistory"); }

    public static PersonTempHistoryDAO getInstance() { return new PersonTempHistoryDAO(); }

}

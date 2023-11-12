/*
* This is Data Access Object Interface
* based on Data Access Object Model.
*/
package com.motel_management.DataAccessObject;

import java.util.ArrayList;

public interface DAOInterface<Obj> {
    int delete(Obj obj);

    int insert(Obj obj);

    int update(Obj obj);

    public Obj selectById(String id);

    public ArrayList<Obj> selectAll();

    public ArrayList<Obj> selectByCondition(String condition);
}

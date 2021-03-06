package com.example.csc685_mobileproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoleDataDao {

    //Get list of roles based on eventid
    @Query("SELECT * FROM roledata WHERE eventid = :eventid")
    List<RoleData> getAll(String eventid);

    @Query("SELECT * FROM roledata WHERE id = :id")
    RoleData get(String id);

    @Insert
    void insertAll(RoleData... role);

    @Update
    void update(RoleData role);

    @Delete
    void delete(RoleData role);
}

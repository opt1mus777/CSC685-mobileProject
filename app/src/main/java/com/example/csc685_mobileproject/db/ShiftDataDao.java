package com.example.csc685_mobileproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShiftDataDao {

    @Query("SELECT * FROM shiftdata where roleid = :roleid")
    List<ShiftData> getAll(String roleid);

    @Query("SELECT * FROM shiftdata WHERE id = :id")
    ShiftData get(String id);

    @Insert
    void insertAll(ShiftData... shifts);

    @Delete
    void delete(ShiftData shift);
}

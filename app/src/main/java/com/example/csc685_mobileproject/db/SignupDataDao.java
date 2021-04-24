package com.example.csc685_mobileproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SignupDataDao {

    @Query("SELECT * FROM signupdata where shiftid = :shiftid")
    List<SignupData> getAll(String shiftid);

    @Query("SELECT * FROM shiftdata WHERE id = :id")
    ShiftData get(String id);

    @Insert
    void insertAll(SignupData... signups);

    @Delete
    void delete(SignupData signup);
}

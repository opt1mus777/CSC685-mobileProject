package com.example.csc685_mobileproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDataDao {
    @Query("SELECT * FROM eventdata")
    List<EventData> getAll();

    @Insert
    void insertAll(EventData... roles);

    @Delete
    void delete(EventData role);
}

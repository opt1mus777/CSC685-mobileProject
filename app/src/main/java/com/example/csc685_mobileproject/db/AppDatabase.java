package com.example.csc685_mobileproject.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.csc685_mobileproject.db.ShiftData;
import com.example.csc685_mobileproject.db.ShiftDataDao;

@Database(entities = {EventData.class, RoleData.class, ShiftData.class, SignupData.class}, version = 14)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShiftDataDao shiftDataDao();
    public abstract EventDataDao eventDataDao();
    public abstract RoleDataDao roleDataDao();
    public abstract SignupDataDao signupDataDao();
}

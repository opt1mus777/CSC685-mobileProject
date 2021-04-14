package com.example.csc685_mobileproject.db;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Room;

import java.util.UUID;

public class DatabaseHelper {

    public static AppDatabase getDB(Context ctx) {
        return Room.databaseBuilder(ctx, AppDatabase.class, "volunteer-db").allowMainThreadQueries().build();
    }

    public static void resetDB(Context ctx) {
        AppDatabase db = DatabaseHelper.getDB(ctx);
        db.clearAllTables();

        EventData e = new EventData();
        e.id = "Annual Fundraiser";
        e.title = "Annual Fundraiser";
        e.description = "Our big annual fundraiser.";

        RoleData gate = new RoleData();
        gate.id = "Gate";
        gate.eventid = e.id;
        gate.title = "Gate";
        gate.description = "Manage gate operations";

        RoleData kitchen = new RoleData();
        kitchen.id = "Kitchen";
        kitchen.eventid = e.id;
        kitchen.title = "Kitchen";
        kitchen.description = "Work in the kitchen";

        RoleData cleanup = new RoleData();
        cleanup.id = "Cleanup";
        cleanup.eventid = e.id;
        cleanup.title = "Cleanup";
        cleanup.description = "Leave no trace!";

        db.eventDataDao().insertAll(e);
        db.roleDataDao().insertAll(gate, kitchen, cleanup);

        for (RoleData role: new RoleData[] {gate, kitchen, cleanup}) {
            for (int i=0; i<4; i++) {
                ShiftData s = new ShiftData();
                s.id = UUID.randomUUID().toString();
                s.roleid = role.id;
                s.time = "Soon to Now";
                s.title = String.format("Task #%d for %s", i, role.title);
                s.description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
                db.shiftDataDao().insertAll(s);
            }
        }
    }
}

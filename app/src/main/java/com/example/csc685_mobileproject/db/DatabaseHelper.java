package com.example.csc685_mobileproject.db;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.csc685_mobileproject.R;

import java.util.UUID;

public class DatabaseHelper {

    public static AppDatabase getDB(Context ctx) {
        RoomDatabase.Callback cb = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                resetDB(db, ctx);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };

        return Room.databaseBuilder(ctx, AppDatabase.class, "volunteer-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(cb)
                .build();
    }

    public static void resetDB(SupportSQLiteDatabase db, Context ctx) {
        String sql = ctx.getString(R.string.initial_data);
        for (String stmt: sql.split(";")) {
            System.out.println(stmt);
            db.execSQL(stmt);
        }
    }
}

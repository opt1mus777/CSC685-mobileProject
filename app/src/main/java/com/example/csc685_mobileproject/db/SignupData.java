package com.example.csc685_mobileproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = ShiftData.class,
        parentColumns = "id",
        childColumns = "shiftid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("shiftid")}
)
public class SignupData {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "shiftid")
    public String shiftid;

}

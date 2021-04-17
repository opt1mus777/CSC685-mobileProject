package com.example.csc685_mobileproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = RoleData.class,
        parentColumns = "id",
        childColumns = "roleid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("roleid")}
)
public class ShiftData {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "roleid")
    public String roleid;

}

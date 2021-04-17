package com.example.csc685_mobileproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = EventData.class,
        parentColumns = "id",
        childColumns = "eventid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("eventid")}
        )
public class RoleData {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "eventid")
    public String eventid;

}

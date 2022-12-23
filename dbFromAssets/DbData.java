package com.example.assignmenttopsnew.dbFromAssets;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbData {
    @PrimaryKey
    int id;
    @ColumnInfo(name="first_name")
    String first_name;
    @ColumnInfo(name="last_name")
    String last_name;
}

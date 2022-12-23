package com.example.assignmenttopsnew.dbFromAssets;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbDao {

    @Query("Select*from Dbdata")
    List<DbData> getAllDbData();

}

package com.example.storageassignment.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.storageassignment.entity.first;

import java.util.List;

@Dao
public interface AssetDao {

    @Query("select * from first")
    List<first> getalldata();
}

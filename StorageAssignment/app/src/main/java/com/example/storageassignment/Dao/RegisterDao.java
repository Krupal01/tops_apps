package com.example.storageassignment.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storageassignment.entity.RegisterEntity;

import java.util.List;

@Dao
public interface RegisterDao {

    @Insert
    void InsertUser(RegisterEntity user);

    @Update
    void updateUser(RegisterEntity user);

    @Delete
    void deleteUser(RegisterEntity user);

    @Query("select * from RegisterEntity")
    List<RegisterEntity> getAllUser();

}

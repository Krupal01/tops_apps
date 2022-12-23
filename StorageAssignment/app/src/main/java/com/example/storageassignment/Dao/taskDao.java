package com.example.storageassignment.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storageassignment.entity.TaskEntity;

import java.util.List;

@Dao
public interface taskDao {

    @Insert
    void insert(TaskEntity task);

    @Delete
    void delete(TaskEntity task);

    @Update
    void update(TaskEntity task);

    @Query("select * from TaskEntity")
    List<TaskEntity> getAlltask();

}

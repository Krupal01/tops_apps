package com.example.storageassignment.taska;

import android.widget.LinearLayout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ATaskDao {
    @Insert
    void TaskInsert(ATaskEntity taskEntity);

    @Delete
    void TaskDelete(ATaskEntity taskEntity);

    @Update
    void TaskUpdate(ATaskEntity taskEntity);

    @Query("select * from ATaskEntity")
    List<ATaskEntity> getAllATask();

    @Query("select * from ATaskEntity ORDER by taskName")
    List<ATaskEntity> SortByName();

    @Query("select * from ATaskEntity ORDER by date")
    List<ATaskEntity> SortByDate();

    @Query("SELECT * FROM ATaskEntity WHERE taskName LIKE :search ")
    List<ATaskEntity> findUserWithName(String search);



}

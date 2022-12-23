package com.example.storageassignment.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storageassignment.entity.NoteEntity;

import java.util.List;

@Dao
public interface noteDao {

    @Insert
    void insertNote(NoteEntity note);

    @Update
    void updateNote(NoteEntity noteEntity);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    @Query("select * from NoteEntity")
    List<NoteEntity> getnoteList();

}

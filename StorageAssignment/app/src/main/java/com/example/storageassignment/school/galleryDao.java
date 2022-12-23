package com.example.storageassignment.school;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface galleryDao {

    @Insert
    void InsertImage(GalleryEntity entity);

    @Query("select * from GalleryEntity")
    List<GalleryEntity> getAllImage();
}

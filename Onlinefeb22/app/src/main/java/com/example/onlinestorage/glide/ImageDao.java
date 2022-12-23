package com.example.onlinestorage.glide;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    void saveImage(ImageData data);

    @Query("select*from ImageData")
    List<ImageData> getImageData();

}

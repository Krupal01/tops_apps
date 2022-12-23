package com.example.onlinestorage.glide;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageData.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();

    private static MyDatabase database;

    public static MyDatabase getInstance(Context context){
        if(database==null){
            database= Room.databaseBuilder(context,MyDatabase.class,"ImageDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}

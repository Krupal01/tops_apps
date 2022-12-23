package com.example.assignmenttopsnew.dbFromAssets;

import android.content.Context;
import android.os.Parcelable;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DbData.class},version = 1,exportSchema = false)
public abstract class DbDatabase extends RoomDatabase {

    public abstract DbDao dbDao();

    private static DbDatabase database;

    public static DbDatabase getInstance(Context context){
        if(database==null){
            database= Room.databaseBuilder(context,DbDatabase.class,"FromAssets")
                    .allowMainThreadQueries()
                    .createFromAsset("demoDatabase.db")
                    .build();
        }
        return database;
    }
}

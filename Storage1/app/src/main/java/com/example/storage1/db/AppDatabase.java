package com.example.storage1.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.storage1.Dao.UserDao;
import com.example.storage1.R;
import com.example.storage1.entity.User;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.app_name)).allowMainThreadQueries().build();
        }
        return db;
    }

}

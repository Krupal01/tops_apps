package com.example.storageassignment.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.storageassignment.Dao.AssetDao;
import com.example.storageassignment.Dao.RegisterDao;
import com.example.storageassignment.Dao.noteDao;
import com.example.storageassignment.Dao.taskDao;
import com.example.storageassignment.entity.first;
import com.example.storageassignment.school.galleryDao;
import com.example.storageassignment.school.GalleryEntity;
import com.example.storageassignment.taska.ATaskDao;
import com.example.storageassignment.taska.ATaskEntity;
import com.example.storageassignment.entity.NoteEntity;
import com.example.storageassignment.entity.RegisterEntity;
import com.example.storageassignment.entity.TaskEntity;
import com.example.storageassignment.R;

@Database(entities = {TaskEntity.class, NoteEntity.class, RegisterEntity.class, ATaskEntity.class, GalleryEntity.class , first.class},version = 1)
public abstract class taskDatabase extends RoomDatabase {

    public abstract taskDao taskDao();
    public abstract noteDao noteDao();
    public abstract RegisterDao RegisterDao();
    public abstract ATaskDao ATaskDao();
    public abstract galleryDao galleryDao();
    public abstract AssetDao assetDao();

    private static taskDatabase db;
    public static taskDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, taskDatabase.class, context.getString(R.string.app_name)).allowMainThreadQueries().build();
        }
        return db;
    }
}

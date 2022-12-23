package com.example.storageassignment.taska;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.PublicKey;
import java.util.Date;

@Entity
public class ATaskEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskName")
    public String taskName;

    @ColumnInfo(name = "taskDetail")
    public String taskDetail;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "timeOfTask")
    public String timeOfTask;

    @ColumnInfo(name = "priority")
    public String priority;

    public ATaskEntity() { }

    protected ATaskEntity(Parcel in) {
        id = in.readInt();
        taskName = in.readString();
        taskDetail = in.readString();
        date = in.readString();
        timeOfTask = in.readString();
        priority = in.readString();
    }

    public static final Creator<ATaskEntity> CREATOR = new Creator<ATaskEntity>() {
        @Override
        public ATaskEntity createFromParcel(Parcel in) {
            return new ATaskEntity(in);
        }

        @Override
        public ATaskEntity[] newArray(int size) {
            return new ATaskEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(taskName);
        dest.writeString(taskDetail);
        dest.writeString(date);
        dest.writeString(timeOfTask);
        dest.writeString(priority);
    }
}

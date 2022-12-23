package com.example.realmstorage.task;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TaskObject extends RealmObject {

    @PrimaryKey
    public  int id;

    public String taskName;

    @Override
    public String toString() {
        return "TaskObject{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}

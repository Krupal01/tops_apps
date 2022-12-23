package com.example.realmstorage.school;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImageObject extends RealmObject {
    @PrimaryKey
    public String Id;

    public String path;

    @Override
    public String toString() {
        return "ImageObject{" +
                "Id='" + Id + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

package com.example.realmstorage.note;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class noteObject extends RealmObject {

    @PrimaryKey
    public String Id;

    public String NoteDetail;

    @Override
    public String toString() {
        return "noteObject{" +
                "Id=" + Id +
                ", NoteDetail='" + NoteDetail + '\'' +
                '}';
    }
}

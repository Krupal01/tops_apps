package com.example.realmstorage.taska;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AtaskObject extends RealmObject {

    @PrimaryKey
    public String Id;

    public String TaskDetail,priority , date ;

}

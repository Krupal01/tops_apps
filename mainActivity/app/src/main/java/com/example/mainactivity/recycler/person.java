package com.example.mainactivity.recycler;

import androidx.dynamicanimation.animation.SpringAnimation;

public class person {
    private String firstname;

    public person(){

    }
    public person(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}

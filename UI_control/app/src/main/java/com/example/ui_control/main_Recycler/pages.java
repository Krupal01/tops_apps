package com.example.ui_control.main_Recycler;

import android.app.Activity;

import androidx.fragment.app.Fragment;

public class pages {
    private String title;
    private Fragment fragment;
    private Class<? extends Activity> activityClass;

    public pages(){

    }

    public pages(String title, Fragment fragment, Class<? extends Activity> activityClass) {
        this.title = title;
        this.fragment = fragment;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }
}
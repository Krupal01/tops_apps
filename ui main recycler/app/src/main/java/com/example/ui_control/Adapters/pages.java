package com.example.ui_control.Adapters;

import androidx.fragment.app.Fragment;

public class pages {
    private String title;
    private Fragment fragment;

    public pages(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public pages(){

    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}

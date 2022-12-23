package com.example.mainactivity.main;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class pages {
    private Fragment fragment;
    private String title;

    public pages() {
    }

    public pages(Fragment fragment , String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}

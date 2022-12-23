package com.example.careercoach.settings;

public class SettingsData {

    String main;
    String sub;

    public SettingsData(String main, String sub) {
        this.main = main;
        this.sub = sub;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}

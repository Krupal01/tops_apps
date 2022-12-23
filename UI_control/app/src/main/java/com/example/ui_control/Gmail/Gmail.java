package com.example.ui_control.Gmail;

public class Gmail {
    private String Title;
    private String Subject;

    public Gmail(String title, String subject) {
        Title = title;
        Subject = subject;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}

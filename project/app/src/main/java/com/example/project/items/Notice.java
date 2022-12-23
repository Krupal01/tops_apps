package com.example.project.items;

public class Notice {
    String Title , NoticeText;

    public Notice(String title, String noticeText) {
        Title = title;
        NoticeText = noticeText;
    }

    public Notice() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNoticeText() {
        return NoticeText;
    }

    public void setNoticeText(String noticeText) {
        NoticeText = noticeText;
    }
}

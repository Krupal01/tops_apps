package com.example.onlinestorage.xml;

public class NewsDataFormat {
    String title, link,description,image;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public NewsDataFormat(String title, String link, String description, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
    }
}

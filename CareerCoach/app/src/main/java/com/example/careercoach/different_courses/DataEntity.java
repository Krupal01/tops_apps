package com.example.careercoach.different_courses;

public class DataEntity {

    private String name,description,duration;

    public DataEntity(){

    }

    public DataEntity(String name,String duration, String description) {
        this.name = name;
        this.description = description;
        this.duration=duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return description;
    }
}

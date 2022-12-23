package com.example.careercoach.different_colleges;

public class Data {

    String name;
    String description;

    public Data(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Data() {

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

    @Override
    public String toString() {
        return description;
    }
}


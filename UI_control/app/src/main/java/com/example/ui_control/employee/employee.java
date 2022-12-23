package com.example.ui_control.employee;

public class employee {

    private String name;
    private String position;
    private String date;
    private Integer src;

    public employee(){

    }

    public employee(String name, String position, String date, Integer src) {
        this.name = name;
        this.position = position;
        this.date = date;
        this.src = src;
    }

    public Integer getSrc() { return src; }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDate() {
        return date;
    }
}

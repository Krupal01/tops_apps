package com.example.project.items;

public class Admin {
    String Name , Password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Admin(String name, String password) {
        Name = name;
        Password = password;
    }
}

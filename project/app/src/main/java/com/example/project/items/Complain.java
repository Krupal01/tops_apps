package com.example.project.items;

public class Complain {
    String name,mobile,aadhar,description,aim,status;

    public Complain(String name, String mobile, String aadhar, String description, String aim) {
        this.name = name;
        this.mobile = mobile;
        this.aadhar = aadhar;
        this.description = description;
        this.aim = aim;
    }

    public Complain() {
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Complain{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", aadhar='" + aadhar + '\'' +
                ", description='" + description + '\'' +
                ", aim='" + aim + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

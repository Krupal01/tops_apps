package com.example.firebase.fcm;

public class user {
    public String firstName, lastName, mobile, Email, Gender;

    public user(String firstName, String lastName, String mobile, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.Email = email;
        this.Gender = gender;
    }

    @Override
    public String toString() {
        return "user{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }
}

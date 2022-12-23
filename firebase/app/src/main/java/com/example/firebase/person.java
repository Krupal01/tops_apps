package com.example.firebase;

public class person {
    String firstName1, lastName1, mobile1, Email1, Gender1;

    public person() {
    }

    @Override
    public String toString() {
        return "person{" +
                "firstName='" + firstName1 + '\'' +
                ", lastName='" + lastName1 + '\'' +
                ", mobile='" + mobile1 + '\'' +
                ", Email='" + Email1 + '\'' +
                ", Gender='" + Gender1 + '\'' +
                '}';
    }

    public String getFirstName1() {
        return firstName1;
    }

    public String getLastName1() {
        return lastName1;
    }

    public String getMobile1() {
        return mobile1;
    }

    public String getEmail1() {
        return Email1;
    }

    public String getGender1() {
        return Gender1;
    }

    public person(String firstName, String lastName, String mobile, String email, String gender) {
        this.firstName1 = firstName;
        this.lastName1 = lastName;
        this.mobile1 = mobile;
        this.Email1 = email;
        this.Gender1 = gender;
    }
}

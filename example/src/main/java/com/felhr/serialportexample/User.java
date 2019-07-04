package com.felhr.serialportexample;

public class User {

    private int usr;
    private String username, email, gender;

    public User(int usr, String username, String email, String gender) {
        this.usr = usr;
        this.username = username;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return usr;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
package com.example.proymovilii.Models;

public class User {
    private int id, phone;
    private String name, email, user, password;

    //Constructores: (alt + insert)
    public User() {
    }

    public User(int phone, String name, String email, String user, String password) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public User(int id, int phone, String name, String email, String user, String password) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

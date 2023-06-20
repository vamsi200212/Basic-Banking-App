package com.androiddev.basicbankingsystem.model;

public class CustomerModel {
    int id,balance;
    String name,email,gender;

    public CustomerModel(int id, String name, String email ,String gender,int balance) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CustomerModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
}

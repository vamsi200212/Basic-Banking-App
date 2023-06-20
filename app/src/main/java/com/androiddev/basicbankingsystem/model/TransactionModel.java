package com.androiddev.basicbankingsystem.model;

public class TransactionModel {

    int id;

    String senderName, receiverName, status, money, senderGender, receiverGender;

    public TransactionModel() {
    }

    public TransactionModel(int id, String senderName, String receiverName, String status, String money, String senderGender, String receiverGender) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = status;
        this.money = money;
        this.senderGender = senderGender;
        this.receiverGender = receiverGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSenderGender() {
        return senderGender;
    }

    public void setSenderGender(String senderGender) {
        this.senderGender = senderGender;
    }

    public String getReceiverGender() {
        return receiverGender;
    }

    public void setReceiverGender(String receiverGender) {
        this.receiverGender = receiverGender;
    }
}

package com.androiddev.basicbankingsystem.params;

public class Params {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "customers_db";
    public static final String TABLE_NAME = "customers_table";
    public static final String TABLE_NAME_1 = "tansactions_table";

    //Keys of our table in db
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_GENDER = "gender";

    //Keys of transaction table
    public static final String TRANSACTION_KEY_ID = "id";
    public static final String TRANSACTION_KEY_SENDER = "sender_name";
    public static final String TRANSACTION_KEY_RECEIVER = "receiver_name";
    public static final String TRANSACTION_KEY_STATUS = "status";
    public static final String TRANSACTION_MONEY = "money";
    public static final String TRANSACTION_KEY_SENDER_GENDER = "sender_gender";
    public static final String TRANSACTION_KEY_RECEIVER_GENDER = "receiver_gender";
}

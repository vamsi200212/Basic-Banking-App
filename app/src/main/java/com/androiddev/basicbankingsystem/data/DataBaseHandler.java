package com.androiddev.basicbankingsystem.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.androiddev.basicbankingsystem.model.CustomerModel;
import com.androiddev.basicbankingsystem.model.TransactionModel;
import com.androiddev.basicbankingsystem.params.Params;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_NAME + " TEXT,"
                + Params.KEY_EMAIL + " TEXT,"
                + Params.KEY_GENDER + " TEXT,"
                + Params.KEY_BALANCE + " INTEGER " + ")";
        String create_trans = "CREATE TABLE " + Params.TABLE_NAME_1 + "("
                + Params.TRANSACTION_KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.TRANSACTION_KEY_SENDER + " TEXT,"
                + Params.TRANSACTION_KEY_RECEIVER + " TEXT,"
                + Params.TRANSACTION_KEY_STATUS + " TEXT,"
                + Params.TRANSACTION_MONEY + " INTEGER,"
                + Params.TRANSACTION_KEY_SENDER_GENDER + " TEXT,"
                + Params.TRANSACTION_KEY_RECEIVER_GENDER + " TEXT "+")";
        Log.d("dbvamsi","Query being runned is : "+ create);
        Log.d("dbvamsi","Query being runned is : "+ create_trans);
        sqLiteDatabase.execSQL(create);
        sqLiteDatabase.execSQL(create_trans);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,customerModel.getName());
        values.put(Params.KEY_EMAIL,customerModel.getEmail());
        values.put(Params.KEY_GENDER,customerModel.getGender());
        values.put(Params.KEY_BALANCE,customerModel.getBalance());

        db.insert(Params.TABLE_NAME,null,values);
        Log.d("addingcustomer","Succesfully added customer : "+customerModel.getName());
        db.close();
    }

    public void addTransaction(TransactionModel transactionModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.TRANSACTION_KEY_SENDER,transactionModel.getSenderName());
        values.put(Params.TRANSACTION_KEY_RECEIVER,transactionModel.getReceiverName());
        values.put(Params.TRANSACTION_KEY_STATUS,transactionModel.getStatus());
        values.put(Params.TRANSACTION_MONEY,transactionModel.getMoney());
        values.put(Params.TRANSACTION_KEY_SENDER_GENDER,transactionModel.getSenderGender());
        values.put(Params.TRANSACTION_KEY_RECEIVER_GENDER,transactionModel.getReceiverGender());
        db.insert(Params.TABLE_NAME_1,null,values);
        Log.d("addingtransaction","Succesfully added transaction : "+transactionModel.getStatus());
        db.close();
    }

    public List<CustomerModel> getAllCustomers(){
        List<CustomerModel> customerModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                CustomerModel customer = new CustomerModel();
                customer.setId(Integer.parseInt(cursor.getString(0)));
                customer.setName(cursor.getString(1));
                customer.setEmail(cursor.getString(2));
                customer.setGender(cursor.getString(3));
                customer.setBalance(Integer.parseInt(cursor.getString(4)));
                customerModels.add(customer);
            }while (cursor.moveToNext());
        }
        return customerModels;
    }

    public List<TransactionModel> getAllTransactions(){
        List<TransactionModel> transactionModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME_1;
        Cursor cursor = db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                TransactionModel transaction = new TransactionModel();
                transaction.setId(Integer.parseInt(cursor.getString(0)));
                transaction.setSenderName(cursor.getString(1));
                transaction.setReceiverName(cursor.getString(2));
                transaction.setStatus(cursor.getString(3));
                transaction.setMoney(cursor.getString(4));
                transaction.setSenderGender(cursor.getString(5));
                transaction.setReceiverGender(cursor.getString(6));
                transactionModels.add(transaction);
            }while (cursor.moveToNext());
        }
        return transactionModels;
    }


    public int updateCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,customerModel.getName());
        values.put(Params.KEY_EMAIL,customerModel.getEmail());
        values.put(Params.KEY_BALANCE,customerModel.getBalance());
        values.put(Params.KEY_GENDER,customerModel.getGender());
//        values.put(Params.KEY_BALANCE,customerModel.getBalance());
//        Log.d("d",customerModel.getId()+" : "+customerModel.getName());

        return db.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?",
                new String[]{String.valueOf(customerModel.getId())});
    }

    public void deleteCustomer(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID +"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int getCountCustomers(){
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public int getCountTransactions(){
        String query = "SELECT * FROM " + Params.TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public void deleteDuplicates(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("DELETE FROM " + Params.TABLE_NAME + " WHERE " + Params.KEY_NAME + " NOT IN (SELECT MIN("+ Params.KEY_NAME +") FROM "+Params.TABLE_NAME+" GROUP BY "+Params.KEY_NAME+")",null);
        db.close();
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Params.TABLE_NAME);
        db.close();
    }
}

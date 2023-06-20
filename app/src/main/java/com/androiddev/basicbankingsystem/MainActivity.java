package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androiddev.basicbankingsystem.data.DataBaseHandler;
import com.androiddev.basicbankingsystem.model.CustomerModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler db = new DataBaseHandler(this);

//        db.deleteTable();
//
        CustomerModel vamsi = new CustomerModel();
        vamsi.setId(1);
        vamsi.setName("Kanuri Teja Sai Vamsi");
        vamsi.setEmail("vamsi200212@gmail.com");
        vamsi.setGender("Male");
        vamsi.setBalance(104500);
        db.addCustomer(vamsi);

        CustomerModel varun = new CustomerModel();
        varun.setId(2);
        varun.setName("Varun Goud");
        varun.setEmail("varun0212@gmail.com");
        varun.setGender("Male");
        varun.setBalance(4500);
        db.addCustomer(varun);

        CustomerModel abhilash = new CustomerModel();
        abhilash.setId(3);
        abhilash.setName("Kanchukota Abhilash");
        abhilash.setEmail("abhilash6102@gmail.com");
        abhilash.setGender("Male");
        abhilash.setBalance(1004500);
        db.addCustomer(abhilash);

        CustomerModel vinay = new CustomerModel();
        vinay.setId(4);
        vinay.setName("Kurakula Vinay Kumar");
        vinay.setEmail("vinay32@gmail.com");
        vinay.setGender("Male");
        vinay.setBalance(500);
        db.addCustomer(vinay);

        CustomerModel sukumar = new CustomerModel();
        sukumar.setId(5);
        sukumar.setName("Bavireddi Sukumar");
        sukumar.setEmail("sukku2003@gmail.com");
        sukumar.setGender("Male");
        sukumar.setBalance(10);
        db.addCustomer(sukumar);

        CustomerModel akshay = new CustomerModel();
        akshay.setId(6);
        akshay.setName("Munagala Akshay Kumar");
        akshay.setEmail("akshaymuna@gmail.com");
        akshay.setGender("Male");
        akshay.setBalance(200000);
        db.addCustomer(akshay);

        CustomerModel aakash = new CustomerModel();
        aakash.setId(7);
        aakash.setGender("Male");
        aakash.setName("Mavurapu Aakash");
        aakash.setEmail("vamsi200212@gmail.com");
        aakash.setBalance(104500);
        db.addCustomer(aakash);

        CustomerModel valli = new CustomerModel();
        valli.setId(8);
        valli.setName("Kanuri Srivalli");
        valli.setEmail("valli1209@gmail.com");
        valli.setGender("Female");
        valli.setBalance(1045);
        db.addCustomer(valli);

        CustomerModel siri = new CustomerModel();
        siri.setId(9);
        siri.setName("Vanapalli Siri Reddy");
        siri.setEmail("sirisha@gmail.com");
        siri.setGender("Female");
        siri.setBalance(50);
        db.addCustomer(siri);

        CustomerModel harini = new CustomerModel();
        harini.setId(10);
        harini.setName("Vanapalli Harini");
        harini.setEmail("vamsi200212@gmail.com");
        harini.setGender("Female");
        harini.setBalance(104);
        db.addCustomer(harini);

        List<CustomerModel> customers = db.getAllCustomers();


//        db.deleteDuplicates();
//        customers = db.getAllCustomers();
//        Log.d("length",customers.size()+"");
//        for(CustomerModel customer: customers){
//            Log.d("print",customer.getId()+" : "+customer.getName());
//        }

        AppCompatButton users_btn;
        users_btn = findViewById(R.id.users_btn);
        users_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UsersActivity.class));
            }
        });

        AppCompatButton transactions = findViewById(R.id.transactions);
        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TransactionActivity.class));
            }
        });
    }
}
package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androiddev.basicbankingsystem.adapters.TransferAdapter;
import com.androiddev.basicbankingsystem.adapters.UsersAdapter;
import com.androiddev.basicbankingsystem.data.DataBaseHandler;
import com.androiddev.basicbankingsystem.model.CustomerModel;

import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends AppCompatActivity {

    RecyclerView rec_view;
    TransferAdapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Bundle bundle = getIntent().getExtras().getBundle("info");
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String email = bundle.getString("mail");
        String gender = bundle.getString("gender");
        int balance = bundle.getInt("balance");

        CustomerModel sample = new CustomerModel(id,name,email,gender,balance);

        DataBaseHandler db = new DataBaseHandler(this);
        List<CustomerModel> customers = db.getAllCustomers();
        ArrayList<CustomerModel> customerModels = new ArrayList<>();

        for(CustomerModel customerModel: customers){
            if(customerModel.getId()!=id){
                customerModels.add(customerModel);
            }
        }

        Toast.makeText(this, ""+customers.size(), Toast.LENGTH_SHORT).show();

        rec_view = findViewById(R.id.rec_view);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransferAdapter(TransferActivity.this,customerModels,sample);
        rec_view.setAdapter(adapter);
        rec_view.setNestedScrollingEnabled(false);
        rec_view.setItemAnimator(null);

        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
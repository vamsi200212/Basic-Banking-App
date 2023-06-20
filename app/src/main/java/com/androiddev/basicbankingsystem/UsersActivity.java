package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androiddev.basicbankingsystem.adapters.UsersAdapter;
import com.androiddev.basicbankingsystem.data.DataBaseHandler;
import com.androiddev.basicbankingsystem.model.CustomerModel;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    RecyclerView rec_view;
    UsersAdapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        DataBaseHandler db = new DataBaseHandler(this);
        List<CustomerModel> customers = db.getAllCustomers();
        Toast.makeText(this, ""+customers.size(), Toast.LENGTH_SHORT).show();

        rec_view = findViewById(R.id.rec_view);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(UsersActivity.this,customers);
        rec_view.setAdapter(adapter);
        rec_view.setNestedScrollingEnabled(false);
        rec_view.setItemAnimator(null);

        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
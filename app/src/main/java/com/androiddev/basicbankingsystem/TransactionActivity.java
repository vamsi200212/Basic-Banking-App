package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androiddev.basicbankingsystem.adapters.TransactionsAdapter;
import com.androiddev.basicbankingsystem.adapters.UsersAdapter;
import com.androiddev.basicbankingsystem.data.DataBaseHandler;
import com.androiddev.basicbankingsystem.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    RecyclerView rec_view;
    TransactionsAdapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        DataBaseHandler db = new DataBaseHandler(this);
        List<TransactionModel> transactionModels = db.getAllTransactions();

        rec_view = findViewById(R.id.rec_view);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionsAdapter(TransactionActivity.this,transactionModels);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
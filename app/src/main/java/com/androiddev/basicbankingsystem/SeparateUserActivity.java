package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SeparateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_user);

        Bundle bundle = getIntent().getExtras().getBundle("info");
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String email = bundle.getString("mail");
        String gender = bundle.getString("gender");
        int balance = bundle.getInt("balance");

        ImageView gender1 = findViewById(R.id.img_gender);
        TextView name1 = findViewById(R.id.name);
        TextView email1 = findViewById(R.id.mail);
        TextView balance1 = findViewById(R.id.acc_bal);
        TextView gender_txt = findViewById(R.id.gender);

        if(gender.equals("Female")){
            Glide.with(this).load(R.drawable.woman).into(gender1);
        }
        name1.setText(name);
        email1.setText(email);
        gender_txt.setText(gender);
        balance1.setText("₹"+balance);

        AppCompatButton btn_transfer;
        btn_transfer = findViewById(R.id.transfer_btn);
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeparateUserActivity.this,TransferActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("id",id);
                bundle1.putString("name",name);
                bundle1.putString("mail",email);
                bundle1.putString("gender",gender);
                bundle1.putInt("balance",balance);
                intent.putExtra("info",bundle1);
                startActivity(intent);
            }
        });

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
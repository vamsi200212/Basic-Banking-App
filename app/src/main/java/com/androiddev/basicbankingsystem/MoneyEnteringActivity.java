package com.androiddev.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androiddev.basicbankingsystem.data.DataBaseHandler;
import com.androiddev.basicbankingsystem.model.CustomerModel;
import com.androiddev.basicbankingsystem.model.TransactionModel;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MoneyEnteringActivity extends AppCompatActivity {

    TextView sender,receiver;
    ImageView img1,img2,cross;
    FloatingActionButton tranfer_fab;
    EditText amount;
    int idSender,idReceiver, balanceSender, balanceReceiver;
    String senderName, receiverName, emailSender, emailReceiver, genderSender, genderReceiver;
    DataBaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_entering);

        sender = findViewById(R.id.name_1);
        receiver = findViewById(R.id.name_2);
        img1 = findViewById(R.id.img_gender_1);
        img2 = findViewById(R.id.img_gender_2);
        cross = findViewById(R.id.back_btn);

        Bundle bundle = getIntent().getExtras().getBundle("infoboth");

        idSender = bundle.getInt("id2");
        idReceiver = bundle.getInt("id1");

        senderName = bundle.getString("name2");
        receiverName = bundle.getString("name1");

        emailSender = bundle.getString("mail2");
        emailReceiver = bundle.getString("mail1");

        balanceSender = bundle.getInt("balance2");
        balanceReceiver = bundle.getInt("balance1");

        genderSender = bundle.getString("gender2");
        genderReceiver = bundle.getString("gender1");

        sender.setText(senderName);
        receiver.setText(receiverName);

        if(genderReceiver.equals("Female")){
            Glide.with(this).load(R.drawable.woman).into(img2);
        }

        if(genderSender.equals("Female")){
            Glide.with(this).load(R.drawable.woman).into(img1);
        }

        amount = findViewById(R.id.amount);

        db = new DataBaseHandler(getApplicationContext());

        tranfer_fab = findViewById(R.id.next_transfer);
        tranfer_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amt = Integer.parseInt(String.valueOf(amount.getText()));
                if((balanceSender-amt)>=0){
                    CustomerModel updatedModelSender = new CustomerModel(idSender,senderName,emailSender,genderSender,balanceSender-amt);
                    CustomerModel updatedModelReceiver = new CustomerModel(idReceiver,receiverName,emailReceiver,genderReceiver,balanceReceiver+amt);
                    DataBaseHandler db = new DataBaseHandler(getApplicationContext());
                    db.updateCustomer(updatedModelReceiver);
                    db.updateCustomer(updatedModelSender);
                    Toast.makeText(MoneyEnteringActivity.this, "sender"+(updatedModelSender.getBalance()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MoneyEnteringActivity.this, "Receiver"+(updatedModelReceiver.getBalance()), Toast.LENGTH_SHORT).show();

                    TransactionModel transaction = new TransactionModel();
                    transaction.setId(db.getCountTransactions()+1);
                    transaction.setSenderName(senderName);
                    transaction.setReceiverName(receiverName);
                    transaction.setStatus("Success");
                    transaction.setMoney(String.valueOf(amt));
                    transaction.setSenderGender(genderSender);
                    transaction.setReceiverGender(genderReceiver);

                    db.addTransaction(transaction);

                    startActivity(new Intent(MoneyEnteringActivity.this,SuccessActivity.class));
                    finish();

                }else{
                    Toast.makeText(MoneyEnteringActivity.this, "Balance Insufficient", Toast.LENGTH_LONG).show();
//                    TransactionModel transaction = new TransactionModel();
//                    transaction.setId(db.getCountTransactions()+1);
//                    transaction.setSenderName(senderName);
//                    transaction.setReceiverName(receiverName);
//                    transaction.setStatus("Failed");
//                    transaction.setMoney(String.valueOf(amt));
//                    transaction.setSenderGender(genderSender);
//                    transaction.setReceiverGender(genderReceiver);
//
//                    db.addTransaction(transaction);
                }
//                Toast.makeText(MoneyEnteringActivity.this, "Sender - "+balanceSender+" Receiver - "+balanceReceiver, Toast.LENGTH_SHORT).show();
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                int amt = Integer.parseInt(String.valueOf(amount.getText()));
//                Toast.makeText(MoneyEnteringActivity.this, "Transaction Cancelled", Toast.LENGTH_LONG).show();
//                TransactionModel transaction = new TransactionModel();
//                transaction.setId(db.getCountTransactions()+1);
//                transaction.setSenderName(senderName);
//                transaction.setReceiverName(receiverName);
//                transaction.setStatus("Failed");
//                transaction.setMoney(String.valueOf(amt));
//                transaction.setSenderGender(genderSender);
//                transaction.setReceiverGender(genderReceiver);
//
//                db.addTransaction(transaction);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int amt = Integer.parseInt(String.valueOf(amount.getText()));
        Toast.makeText(MoneyEnteringActivity.this, "Transaction Cancelled", Toast.LENGTH_LONG).show();
        TransactionModel transaction = new TransactionModel();
        transaction.setId(db.getCountTransactions()+1);
        transaction.setSenderName(senderName);
        transaction.setReceiverName(receiverName);
        transaction.setStatus("Failed");
        transaction.setMoney(String.valueOf(amt));
        transaction.setSenderGender(genderSender);
        transaction.setReceiverGender(genderReceiver);

        db.addTransaction(transaction);
    }
}
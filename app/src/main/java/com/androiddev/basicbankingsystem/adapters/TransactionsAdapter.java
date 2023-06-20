package com.androiddev.basicbankingsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.basicbankingsystem.MoneyEnteringActivity;
import com.androiddev.basicbankingsystem.R;
import com.androiddev.basicbankingsystem.SeparateUserActivity;
import com.androiddev.basicbankingsystem.model.CustomerModel;
import com.androiddev.basicbankingsystem.model.TransactionModel;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.myViewHolder> {
    private List<TransactionModel> categories;
    Context context;

    public TransactionsAdapter(Context context, List<TransactionModel> categories){
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item_view,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.myViewHolder holder, int position) {
//        holder.balance.setText("₹"+categories.get(position).getBalance()+"");
//        holder.name.setText(categories.get(position).getName()+"");
//        if(categories.get(position).getGender().equals("Female")){
//            Glide.with(holder.gender).load(R.drawable.woman).into(holder.gender);
//        }

        holder.senderName.setText(categories.get(position).getSenderName());
        holder.reciverName.setText(categories.get(position).getReceiverName());

        if(categories.get(position).getSenderGender().equals("Female")){
            Glide.with(holder.senderGender).load(R.drawable.woman).into(holder.senderGender);
        }

        if(categories.get(position).getReceiverGender().equals("Female")){
            Glide.with(holder.receiverGender).load(R.drawable.woman).into(holder.receiverGender);
        }

        holder.trans_amt.setText(categories.get(position).getMoney());
        if(categories.get(position).getStatus().equals("Failed")){
            holder.status.setText("Transaction Failed");
            holder.status.setBackgroundResource(R.drawable.green_bg);
        }else{
            holder.status.setText("Transaction Success");
            holder.status.setTextColor(Color.parseColor("#000000"));
            holder.status.setBackgroundResource(R.drawable.green_bg_1);
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView senderGender, receiverGender;
        TextView senderName, reciverName, trans_amt, status;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            trans_amt = itemView.findViewById(R.id.amt);
            senderGender = itemView.findViewById(R.id.img_gender);
            receiverGender = itemView.findViewById(R.id.img_gender_1);
            senderName = itemView.findViewById(R.id.name);
            reciverName = itemView.findViewById(R.id.name_1);
            status = itemView.findViewById(R.id.status);
        }

    }
}


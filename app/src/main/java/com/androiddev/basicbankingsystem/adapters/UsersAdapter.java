package com.androiddev.basicbankingsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.basicbankingsystem.R;
import com.androiddev.basicbankingsystem.SeparateUserActivity;
import com.androiddev.basicbankingsystem.model.CustomerModel;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.myViewHolder> {
    private List<CustomerModel> categories;
    Context context;

    public UsersAdapter(Context context, List<CustomerModel> categories){
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item_view,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.myViewHolder holder, int position) {
        holder.balance.setText("₹"+categories.get(position).getBalance()+"");
        holder.name.setText(categories.get(position).getName()+"");
        if(categories.get(position).getGender().equals("Female")){
            Glide.with(holder.gender).load(R.drawable.woman).into(holder.gender);
        }
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name",categories.get(holder.getAdapterPosition()).getName());
                bundle.putString("mail",categories.get(holder.getAdapterPosition()).getEmail());
                bundle.putString("gender",categories.get(holder.getAdapterPosition()).getGender());
                bundle.putInt("id",categories.get(holder.getAdapterPosition()).getId());
                bundle.putInt("balance",categories.get(holder.getAdapterPosition()).getBalance());
                Intent intent = new Intent(context, SeparateUserActivity.class);
                intent.putExtra("info",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView gender;
        TextView name, balance;
        ImageView next;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            gender = itemView.findViewById(R.id.img_gender);
            name = itemView.findViewById(R.id.name);
            balance = itemView.findViewById(R.id.acc_bal);
            next = itemView.findViewById(R.id.next_btn);
        }

    }
}


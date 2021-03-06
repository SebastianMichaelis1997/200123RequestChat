package com.sebisoftworks.a200123requestchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyViewholder> {

    ArrayList<Message> mData;

    public MyAdapter(ArrayList<Message> aData) {
        mData = aData;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View newView = inflater.inflate(R.layout.message_element, parent, false);
        MyViewholder viewholder = new MyViewholder(newView);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int pos) {
        Message message = mData.get(pos);
        holder.tv_message.setText(message.getMessage());
        holder.tv_date.setText(message.getDate().toString());
        holder.tv_from.setText(message.getFrom());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
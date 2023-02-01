package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<Item> list;
    private Context context;
    public  ViewAdapter(Context context, ArrayList<Item> list){
        this.list=list;
        this.context =context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new MyViewHolder(view);
    }
    public void  setValue( ArrayList<Item> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.header.setText( list.get(position).header);
        holder.counter.setText(list.get(position).rate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

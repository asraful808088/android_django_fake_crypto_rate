package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView header;
    public TextView counter;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.header);
        counter = itemView.findViewById(R.id.counter);
    }
}

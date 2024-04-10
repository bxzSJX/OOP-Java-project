package com.example.oopandroidapi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = itemView.findViewById(R.id.t1);

    }
}

package com.example.oopandroidapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainSearchAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<String> a;
    private Context context;
    public MainSearchAdapter(List<String> a, Context context){
        this.a = a;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view, parent, false);
        MainViewHolder mainViewHolder = new MainViewHolder(v);

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String searchItem = a.get(position);
        holder.t1.setText(searchItem);
    }

    @Override
    public int getItemCount() {
        return a.size();
    }
}

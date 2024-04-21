package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public class ComparisonFragment extends Fragment {
    private String name;
    private String population;
    private String employmentrate;
    private String workplace;
    private String weather;
    private String politicaldistribution;
    private ViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_compare, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDataBindings(view);
    }

    private void setupDataBindings(View view) {
        TextView tvPopulation = view.findViewById(R.id.tvPopulation);
        TextView tvWeather
        // Repeat for other TextViews

        viewModel.getPopulation().observe(getViewLifecycleOwner(), population -> {
            tvPopulation.setText("Population: " + population);
        });

        // Repeat observers setup for other data
    }

}


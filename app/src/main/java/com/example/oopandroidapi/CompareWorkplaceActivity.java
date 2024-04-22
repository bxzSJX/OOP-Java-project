package com.example.oopandroidapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompareWorkplaceActivity extends AppCompatActivity {
    private String currentCityName;
    private String previousCityName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_workplace);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");

        TextView textViewPreviousCityName = findViewById(R.id.city1);
        TextView textViewCurrentCityName = findViewById(R.id.city2);
        if (currentCityName != null){
            textViewCurrentCityName.setText(currentCityName);
        }
        if (previousCityName != null){
            textViewPreviousCityName.setText(previousCityName);
        }
    }
}

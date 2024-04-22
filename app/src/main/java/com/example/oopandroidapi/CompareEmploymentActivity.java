package com.example.oopandroidapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompareEmploymentActivity extends AppCompatActivity {
    private String currentCityName;
    private String previousCityName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_employment);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");

        TextView textViewPreviousCityName = findViewById(R.id.City11);
        TextView textViewCurrentCityName = findViewById(R.id.City22);
        if (currentCityName != null){
            textViewCurrentCityName.setText(currentCityName);
        }
        if (previousCityName != null){
            textViewPreviousCityName.setText(previousCityName);
        }
    }
}

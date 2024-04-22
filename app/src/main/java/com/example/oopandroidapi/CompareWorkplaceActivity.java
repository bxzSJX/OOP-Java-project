package com.example.oopandroidapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompareWorkplaceActivity extends AppCompatActivity {
    private String currentCityName;
    private String previousCityName;
    ArrayList<WorkplaceData> CurrentWorkplace;
    ArrayList<WorkplaceData> PreviousWorkplace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_workplace);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");
        TextView textViewPreviousCityName = findViewById(R.id.city1);
        TextView textViewCurrentCityName = findViewById(R.id.city2);
        TextView textViewPreviousCity = findViewById(R.id.previouscity);
        TextView textViewCurrentCity= findViewById(R.id.currentcity);
        TextView textViewcomparsion = findViewById(R.id.compare);
        if (currentCityName != null){
            textViewCurrentCityName.setText(currentCityName);
        }
        if (previousCityName != null){
            textViewPreviousCityName.setText(previousCityName);
        }
        WorkplaceDataRetriever workplaceDataRetriever = new WorkplaceDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                MultipalDataRetriever.getMunicipalityCodeMap();

            }
        });
    }
}
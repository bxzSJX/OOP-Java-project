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
    ArrayList<WorkEfficiencyData> CurrentWorkplace;
    ArrayList<WorkEfficiencyData> PreviousWorkplace;
    private double previousCityWorkplace;
    private double currentCityWorkplace;

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
                WorkplaceDataRetriever.getMunicipalityCodeMap();
                CurrentWorkplace = workplaceDataRetriever.getWorkEfficiency(getApplicationContext(),currentCityName);
                PreviousWorkplace = workplaceDataRetriever.getWorkEfficiency(getApplicationContext(),previousCityName);
                runOnUiThread(()->{
                    if (!CurrentWorkplace.isEmpty()) {
                        currentCityWorkplace = CurrentWorkplace.get(0).getSelfEfficiency();
                        textViewCurrentCity.setText("Workplace self-sufficiency (2022): " + currentCityWorkplace+ "%");
                    }
                    if (!PreviousWorkplace.isEmpty()) {
                        previousCityWorkplace = PreviousWorkplace.get(0).getSelfEfficiency();
                        textViewPreviousCity.setText("Workplace self-sufficiency(2022): " +previousCityWorkplace + "%");
                    }
                    if (currentCityWorkplace > previousCityWorkplace) {
                        textViewcomparsion.setText(currentCityName + " has a larger self-sufficiency than " + previousCityName);
                    } else if (currentCityWorkplace <previousCityWorkplace) {
                        textViewcomparsion.setText(previousCityName + " has a larger self-sufficiency than " + currentCityName);
                    } else {
                        textViewcomparsion.setText("Both cities have the same population");
                    }


            });
            }
        });
    }
}
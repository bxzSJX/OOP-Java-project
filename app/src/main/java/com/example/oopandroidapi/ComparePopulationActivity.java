package com.example.oopandroidapi;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparePopulationActivity extends AppCompatActivity {
    private String currentCityName;
    private String previousCityName;
    ArrayList<PopulationData> CURRpopulation;
    ArrayList<PopulationData> Prepopulation;
    private int previousCityPopulation;
    private int currentCityPopulation;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_population);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");


        TextView textViewPreviousCityName = findViewById(R.id.City1);
        TextView textViewCurrentCityName = findViewById(R.id.Cityname2);
        TextView textViewPreviousCityPopulation = findViewById(R.id.Population1);
        TextView textViewCurrentCityPopulation = findViewById(R.id.Population2);
        TextView textViewcomparsion = findViewById(R.id.comparsion);
        if (currentCityName != null){
            textViewCurrentCityName.setText(currentCityName);
        }
        if (previousCityName != null){
            textViewPreviousCityName.setText(previousCityName);
        }
        MultipalDataRetriever multipalDataRetriever = new MultipalDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                MultipalDataRetriever.getMunicipalityCodeMap();
                CURRpopulation = multipalDataRetriever.getPopulation(getApplicationContext(),currentCityName);
                Prepopulation = multipalDataRetriever.getPopulation(getApplicationContext(),previousCityName);
                runOnUiThread(()->{
                    if (!CURRpopulation.isEmpty()) {
                        currentCityPopulation = CURRpopulation.get(0).getPopulation();
                        textViewCurrentCityPopulation.setText("Population (2022): " + currentCityPopulation);
                    }
                    if (!Prepopulation.isEmpty()) {
                       previousCityPopulation = Prepopulation.get(0).getPopulation();
                        textViewPreviousCityPopulation.setText("Population (2022): " +previousCityPopulation);
                    }
                    if (currentCityPopulation > previousCityPopulation) {
                        textViewcomparsion.setText(currentCityName + " has a larger population than " + previousCityName);
                    } else if (currentCityPopulation <previousCityPopulation) {
                        textViewcomparsion.setText(previousCityName + " has a larger population than " + currentCityName);
                    } else {
                        textViewcomparsion.setText("Both cities have the same population");
                    }
                    
                });

            }
        });

    }

}

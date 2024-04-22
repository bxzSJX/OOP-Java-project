package com.example.oopandroidapi;

import android.os.Bundle;
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
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_population);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");
        TextView textViewPreviousCityName = findViewById(R.id.City1);
        TextView textViewCurrentCityName = findViewById(R.id.Cityname2);
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
                for (PopulationData populationData: CURRpopulation){
                    System.out.println(populationData.getPopulation()
                    +"-------");
                }
            }
        });
    }

}

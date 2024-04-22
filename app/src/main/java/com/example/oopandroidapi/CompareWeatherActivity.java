package com.example.oopandroidapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompareWeatherActivity extends AppCompatActivity {
    private String currentCityName;
    private String previousCityName;
    private TextView previouscityweather;
    private TextView currentcityweather;
    private WeatherDataRetriever weatherDataRetriever;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentweather);
        currentCityName = getIntent().getStringExtra("currentCityName");
        previousCityName = getIntent().getStringExtra("previousCityName");

        TextView textViewPreviousCityName = findViewById(R.id.CityName1);
        TextView textViewCurrentCityName = findViewById(R.id.CityName2);
        TextView textViewPreviousCityTemp = findViewById(R.id.previouscityweather);
        TextView textViewCurrentCityTemp = findViewById(R.id.currentcityweather);
        TextView textViewCompare = findViewById(R.id.compare);
        if (currentCityName != null){
            textViewCurrentCityName.setText(currentCityName);
        }
        if (previousCityName != null){
            textViewPreviousCityName.setText(previousCityName);
        }
        weatherDataRetriever.getData(currentCityName, new WeatherDataRetriever.WeatherDataCallback() {
            @Override
            public void onWeatherDataReceived(WeatherData weatherData) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String temperature = weatherData.getTemperature() + " °F";
                        textViewCurrentCityTemp.setText(temperature);

                    }
                });
            }
        });
        weatherDataRetriever.getData(previousCityName, new WeatherDataRetriever.WeatherDataCallback() {
            @Override
            public void onWeatherDataReceived(WeatherData weatherData) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String temperature = weatherData.getTemperature() + " °F";
                        textViewPreviousCityTemp.setText(temperature);

                    }
                });
            }
        });


    };


    }





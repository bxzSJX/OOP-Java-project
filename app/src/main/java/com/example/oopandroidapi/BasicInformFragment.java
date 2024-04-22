package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicInformFragment extends Fragment {

    private City city;
    private String name;

    private TextView cityname, weatherdescr, temp, windspeed,population;
    private ImageView imageWeather;
    private int cityPopulation;
    private WeatherDataRetriever weatherDataRetriever;
    private MultipalDataRetriever multipalDataRetriever;
    private final String IMG_URL = "http://openweathermap.org/img/w/";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_basic, container, false);
        cityname = v.findViewById(R.id.cityName);
        weatherdescr = v.findViewById(R.id.weatherDescr);
        temp = v.findViewById(R.id.temp);
        windspeed = v.findViewById(R.id.windSpeed);
        imageWeather = v.findViewById(R.id.imageWeather);
        population = v.findViewById(R.id.population);
        multipalDataRetriever = new MultipalDataRetriever();
        weatherDataRetriever = new WeatherDataRetriever();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                MultipalDataRetriever.getMunicipalityCodeMap();
                ArrayList<PopulationData> population01 = multipalDataRetriever.getPopulation(getActivity(), name);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(PopulationData populationData:population01){
                            cityPopulation = populationData.getPopulation();

                        }
                        Log.d(TAG, String.valueOf(cityPopulation));
                        population.setText("population(2022): " + String.valueOf(cityPopulation));
                    }
                });
                weatherDataRetriever.getData(name, new WeatherDataRetriever.WeatherDataCallback() {
                    @Override
                    public void onWeatherDataReceived(WeatherData weatherData) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cityname.setText(weatherData.getName());
                                weatherdescr.setText(weatherData.getDescription());
                                String temperature = weatherData.getTemperature() + " °F";
                                temp.setText(temperature);
                                String speed = weatherData.getWindSpeed() + " m/s";
                                windspeed.setText(speed);
                                String weatherIcon = IMG_URL + weatherData.getIcon() + ".png";
                                Glide.with(BasicInformFragment.this).load(weatherIcon).into(imageWeather);
                            }
                        });
                    }
                });
            }
        });

        return v;

    }

    public void setCityName(String Name) {
        this.name = Name;
    }

}

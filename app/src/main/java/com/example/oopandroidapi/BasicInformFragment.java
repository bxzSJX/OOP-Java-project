package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicInformFragment extends Fragment {

    private City city;
    private String name;
    private TextView cityname, weatherdescr, temp, windspeed;
    private ImageView imageWeather;
    private WeatherDataRetriever weatherDataRetriever;
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
        weatherDataRetriever = new WeatherDataRetriever();
        weatherDataRetriever.getData(name, new WeatherDataRetriever.WeatherDataCallback() {
            @Override
            public void onWeatherDataReceived(WeatherData weatherData) {
                getActivity().runOnUiThread(()->{
                    cityname.setText(weatherData.getName());
                    weatherdescr.setText(weatherData.getDescription());
                    String temperature = weatherData.getTemperature() + " °F";
                    temp.setText(temperature);
                    String speed = weatherData.getWindSpeed() + " m/s";
                    windspeed.setText(speed);
                    String weatherIcon = IMG_URL + weatherData.getIcon() + ".png";
                    Log.d(TAG,weatherIcon + "!!!!");
                    Glide.with(BasicInformFragment.this).load(weatherIcon).into(imageWeather);
                });
            }
        });
        return v;

    }

    public void setCityName(String Name) {
        this.name = Name;
    }

}

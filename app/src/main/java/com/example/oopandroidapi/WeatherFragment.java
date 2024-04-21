package com.example.oopandroidapi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class WeatherFragment extends Fragment {

    private SharedViewModel viewModel;
    private ComparsionInfoAdapter adapter;
    private ViewPager2 viewPager;
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState){
       View view =  inflater.inflate(R.layout.fragmentweather,container,false);
       viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
       TextView currentCityWeatherView = view.findViewById(R.id.currentcityweather);
       TextView previousCityWeatherView = view.findViewById(R.id.previouscityweather);
       TextView previousCityNameView = view.findViewById(R.id.CityName1);
       TextView currentCityNameView = view.findViewById(R.id.CityName2);
       viewModel.getCurrentCityName().observe(getViewLifecycleOwner(), newCityName -> {
           if (newCityName != null && !newCityName.isEmpty()) {
               currentCityNameView.setText(newCityName);
           }
       });
       viewModel.getPreviousCityName().observe(getViewLifecycleOwner(), oldCityName -> {
           if (oldCityName != null && !oldCityName.isEmpty()) {
               previousCityNameView.setText(oldCityName);
           }
       });

        viewModel.getCurrentCityWeather().observe(getViewLifecycleOwner(), new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                if (weatherData != null) {
                    currentCityWeatherView.setText(formatWeatherData(weatherData));
                }
            }
        });
        viewModel.getPreviousCityWeather().observe(getViewLifecycleOwner(), new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                if (weatherData != null) {
                    previousCityWeatherView.setText(formatWeatherData(weatherData));
                }
            }
        });

        return view;
    }

    private String formatWeatherData(WeatherData weatherData){
        return "Temperature: " + weatherData.getTemperature() +"\n"
                + "Description: " + weatherData.getDescription() + "\n"
                + "Wind speed: " + weatherData.getWindSpeed();
    }
    public void refreshWeatherData(String currentCity, String previousCity){
        if (viewModel!=null) {
            viewModel.refreshWeather(currentCity, previousCity);
        }
    }
    public WeatherFragment findWeatherFragment(){
        if (viewPager.getCurrentItem() == 3) {
            return (WeatherFragment) adapter.createFragment(3);
        }
        return null;
    }
}

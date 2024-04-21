package com.example.oopandroidapi;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<WeatherData> currentCityWeather = new MutableLiveData<>();
    private final MutableLiveData<WeatherData> previousCityWeather = new MutableLiveData<>();
    private WeatherDataRetriever weatherDataRetriever;
    private final MutableLiveData<String> currentCityName = new MutableLiveData<>();
    private final MutableLiveData<String> previousCityName = new MutableLiveData<>();
    public SharedViewModel(){
        weatherDataRetriever = new WeatherDataRetriever();
    }
    public void setCurrentCityName(String name){
        currentCityName.setValue(name);
    }
    public void setPreviousCityName(String name){
       previousCityName.setValue(name);
    }
    public LiveData<String> getCurrentCityName() {
        return currentCityName;
    }

    public LiveData<String> getPreviousCityName() {
        return previousCityName;
    }

    public LiveData<WeatherData> getCurrentCityWeather() {
        return currentCityWeather;
    }
    public void setCurrentCityWeather(WeatherData data){
        currentCityWeather.setValue(data);
    }
    public LiveData<WeatherData> getPreviousCityWeather(){
        return previousCityWeather;
    }
    public void setPreviousCityWeather(WeatherData data){
        previousCityWeather.setValue(data);
    }
    public void refreshWeather(String currentCity, String previousCity){
        if (currentCity!=null &&!currentCity.isEmpty()) {
            weatherDataRetriever.getData(currentCity, new WeatherDataRetriever.WeatherDataCallback() {
                @Override
                public void onWeatherDataReceived(WeatherData weatherData) {
                    Log.d("SharedViewModel", "Weather data received for current city: " + weatherData);
                    currentCityWeather.postValue(weatherData);
                }
            });
        }

        if (previousCity!=null &&!previousCity.isEmpty()){
            weatherDataRetriever.getData(previousCity, new WeatherDataRetriever.WeatherDataCallback() {
                @Override
                public void onWeatherDataReceived(WeatherData weatherData) {
                    previousCityWeather.postValue(weatherData);
                }
            });




}}}

package com.example.oopandroidapi;

public class CityHistory {
    private City previousCity;
    private City currentCity;
    public void addCurrentCity (City city){
        if(currentCity != null){
            previousCity = currentCity;
        }
        currentCity = city;
    }
    public WeatherData getPreviousCityWeather(){
        if (previousCity!= null){
            return previousCity.getWeatherData();
        }
        return null;
    }
    public WeatherData getCurrentCityWeather(){
        if (currentCity!= null){
            return currentCity.getWeatherData();
        }
        return null;
    }
    public void addPreviousCity (City city){
        previousCity = city;
    }
}

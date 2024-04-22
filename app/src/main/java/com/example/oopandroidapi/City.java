package com.example.oopandroidapi;

public class City {
    private String cityname;
    public PopulationData populationData;

    public WeatherData weatherData;

    public EmploymentData employmentData;


    public City(String cityname){
        this.cityname = cityname;
    }
    public String getCityname(){
        return cityname;
    }

    public WeatherData getWeatherData(){
        return weatherData;
    }

}

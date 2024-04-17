package com.example.oopandroidapi;

public class CityHistory {
    public City previousCity;
    public City currentCity;
    public void addCurrentCity (City city){
        currentCity = city;
    }
    public void addPreviousCity (City city){
        previousCity = city;
    }
}

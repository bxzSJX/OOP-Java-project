package com.example.oopandroidapi;

public class PopulationIncreaseData {
    private int year;
    private int populationincrease;
    public PopulationIncreaseData(int year, int populationincrease){
        this.populationincrease = populationincrease;
        this.year = year;
    }
    public int getYear(){
        return year;
    }
    public int getPopulationincrease(){
        return populationincrease;
    }
}

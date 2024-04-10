package com.example.oopandroidapi;

import java.math.BigDecimal;

public class PopulationData {
    private int year;
    private BigDecimal population;
    public PopulationData(int year, BigDecimal population){
        this.population = population;
        this.year = year;
    }
    public int getYear(){
        return year;
    }
    public  BigDecimal getPopulation(){
        return population;
    }
    public void setYear(){
        this.year = year;
    }
    public void setPopulation(){
        this.population = population;
    }
}

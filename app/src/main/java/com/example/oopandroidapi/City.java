package com.example.oopandroidapi;

public class City {
    private String cityname;
    public PopulationData populationData;//保存之前查的数据
    //创建一个新类 保存cityhistory as a list
    public WeatherData weatherData;

    public City(String cityname){
        this.cityname = cityname;
    }
    public String getCityname(){
        return cityname;
    }

}

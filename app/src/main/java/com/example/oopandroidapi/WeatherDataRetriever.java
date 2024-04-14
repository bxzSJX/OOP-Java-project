package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

import android.location.Geocoder;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherDataRetriever {

    private final String API_KEY =  "fb8c1c7414c3e36d2ab4a5dec6c2dd5a";

    // https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    // http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&appid={API key}
    private  final String GEOLOCATION_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s";
    private final String IMG_URL = "http://openweathermap.org/img/w/";


    public void getData(String municipalityName, WeatherDataCallback callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() ->{
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode areas = null;
            try {
                // String.format replaces %s with the parameters
                // municipalityName (city name),1 (limit, aka how many cities will be returned) and API_KEY
                URL locationUrl = new URL(String.format(GEOLOCATION_API_URL, municipalityName, API_KEY));
                areas = objectMapper.readTree(locationUrl);
                String latitude = areas.get(0).get("lat").toString();
                String longitude = areas.get(0).get("lon").toString();
                URL weatherUrl = new URL(String.format(WEATHER_API_URL, latitude, longitude, API_KEY));
                JsonNode weatherJson = objectMapper.readTree(weatherUrl);
                WeatherData weatherData = new WeatherData(
                        weatherJson.get("name").asText(),
                        weatherJson.get("weather").get(0).get("icon").asText(),
                        weatherJson.get("weather").get(0).get("main").asText(),
                        weatherJson.get("weather").get(0).get("description").asText(),
                        weatherJson.get("main").get("temp").asText(),
                        weatherJson.get("wind").get("speed").asText()
                );
                callback.onWeatherDataReceived(weatherData);
            }
            catch (IOException e) {

                e.printStackTrace();

            }
        });

    }
    public interface WeatherDataCallback {
        void onWeatherDataReceived(WeatherData weatherData);
    }


}

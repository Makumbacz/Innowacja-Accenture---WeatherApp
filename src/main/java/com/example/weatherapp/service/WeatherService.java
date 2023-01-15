package com.example.weatherapp.service;

import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.repository.ForecastRepository;
import com.example.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;
    private final ForecastRepository forecastRepository;

    public Weather getNewWeatherForCity(JSONObject jsonObjectForCurrentWeather){
        String cityName;
        try {
            cityName = jsonObjectForCurrentWeather.get("name").toString();
        }catch (JSONException e){
            jsonObjectForCurrentWeather.get("city");
            cityName = ((JSONObject) jsonObjectForCurrentWeather.get("city")).get("name").toString();
        }
        City city = cityRepository.findByName(cityName);
        if(city == null){
            city = setupCityWithNewWeather(jsonObjectForCurrentWeather, cityName);
        }else{
            city.getWeather().setWeatherFromJsonObject(jsonObjectForCurrentWeather);
        }
        return city.getWeather();
    }

    private City setupCityWithNewWeather(JSONObject jsonObject, String cityName) {
        City city;
        double coordLon;
        double coordLat;
        Weather newWeather =  new Weather();
        newWeather.setWeatherFromJsonObject(jsonObject);
        try {
             coordLon = Double.parseDouble(((JSONObject) jsonObject.get("coord")).get("lon").toString());
             coordLat = Double.parseDouble(((JSONObject) jsonObject.get("coord")).get("lat").toString());
        }catch (JSONException e){
             coordLon = Double.parseDouble(((JSONObject) ((JSONObject) jsonObject.get("city")).get("coord")).get("lon").toString());
             coordLat = Double.parseDouble(((JSONObject) ((JSONObject) jsonObject.get("city")).get("coord")).get("lat").toString());
        }
           log.warn("City {} not found in db! Adding it to db...", cityName);
        city = new City(cityName, coordLat, coordLon, newWeather);
        cityService.saveCity(city);
        weatherRepository.save(newWeather);
        city.setWeather(newWeather);
        newWeather.setCity(city);

        return city;
    }



}


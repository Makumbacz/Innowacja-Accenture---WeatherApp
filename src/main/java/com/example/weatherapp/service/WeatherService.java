package com.example.weatherapp.service;

import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;

    public Weather getNewWeatherForCity(JSONObject jsonObject){

        String cityName = jsonObject.get("name").toString();
        City city = cityRepository.findByName(cityName);
        if(city == null){
            Weather newWeather =  new Weather();
            newWeather.setWeatherFromJsonObject(jsonObject);

            double coordLon = Double.parseDouble(((JSONObject)jsonObject.get("coord")).get("lon").toString());
            double coordLat = Double.parseDouble(((JSONObject)jsonObject.get("coord")).get("lat").toString());
            log.warn("City {} not found in db! Adding it to db...", cityName);
            city = new City();
            city.setName(cityName);
            city.setLongitude(coordLon);
            city.setLatitude(coordLat);
            city.setWeather(newWeather);
            cityService.saveCity(city);
            weatherRepository.save(newWeather);
        }else{
            city.getWeather().setWeatherFromJsonObject(jsonObject);
        }

        return city.getWeather();
    }

}


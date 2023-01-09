package com.example.weatherapp.service;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class WeatherService {
    private final WeatherRepository weatherRepository;

    public Weather saveWeather(JSONObject jsonObject){
        Weather newWeather =  new Weather();

        return newWeather;
    }

}


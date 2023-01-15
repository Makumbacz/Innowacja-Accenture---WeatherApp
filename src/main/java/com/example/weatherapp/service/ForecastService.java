package com.example.weatherapp.service;

import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.repository.ForecastRepository;
import com.example.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ForecastService {


    private final WeatherRepository weatherRepository;
    private final WeatherService weatherService;
    private final CityRepository cityRepository;
    private final CityService cityService;
    private final ForecastRepository forecastRepository;
    public Forecast getNewForecastForCity(JSONObject jsonObjectForecast){
        JSONArray JSONList =  (jsonObjectForecast.getJSONArray("list"));
        JSONObject JSONCity = jsonObjectForecast.getJSONObject("city");
        JSONObject jsonObjectForCurrentWeather = new JSONObject();
        if(JSONList.length() > 0 && JSONList.get(0) instanceof JSONObject) {
            jsonObjectForCurrentWeather.put("d", JSONList.getJSONObject(0));
        }
        ((JSONObject)jsonObjectForCurrentWeather.get("d")).put("coord",JSONCity.getJSONObject("coord"));
        log.info(JSONCity.getString("name"));
        ((JSONObject)jsonObjectForCurrentWeather.get("d")).put("name",JSONCity.getString("name"));


        Weather weather = weatherService.getNewWeatherForCity(jsonObjectForCurrentWeather.getJSONObject("d"));
        City city = weather.getCity();
        Forecast forecast = new Forecast();
        List<Weather> weathers = new ArrayList<>();
        weathers.add(weather);
        for (int i = 1; i < JSONList.length() ; i++) {
            Weather newWeather = new Weather().setWeatherFromJsonObject(JSONList.getJSONObject(i));
            newWeather.setCity(city);
            newWeather.setForecast(forecast);
            weathers.add(newWeather);

        }
        forecast.setWeathers(weathers);
        forecastRepository.save(forecast);
        return forecast;
    }
}

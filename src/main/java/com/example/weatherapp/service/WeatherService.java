package com.example.weatherapp.service;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.repository.ForecastRepository;
import com.example.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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
            city.getWeather().get(city.getWeather().size() - 1).setWeatherFromJsonObject(jsonObjectForCurrentWeather);
        }
        return city.getWeather().get(city.getWeather().size() - 1);
    }

    private City setupCityWithNewWeather(JSONObject jsonObject, String cityName) {
        City city;
        double coordLon;
        double coordLat;
        List<Weather> WeatherList =  new ArrayList<>();
        Weather newWeather = new Weather().setWeatherFromJsonObject(jsonObject);
        WeatherList.add(newWeather);
        try {
             coordLon = Double.parseDouble(((JSONObject) jsonObject.get("coord")).get("lon").toString());
             coordLat = Double.parseDouble(((JSONObject) jsonObject.get("coord")).get("lat").toString());
        }catch (JSONException e){
             coordLon = Double.parseDouble(((JSONObject) ((JSONObject) jsonObject.get("city")).get("coord")).get("lon").toString());
             coordLat = Double.parseDouble(((JSONObject) ((JSONObject) jsonObject.get("city")).get("coord")).get("lat").toString());
        }
           log.warn("City {} not found in db! Adding it to db...", cityName);
        city = new City(cityName, coordLat, coordLon, WeatherList);
        cityService.saveCity(city);
        weatherRepository.save(WeatherList.get(0));
        city.setWeather(WeatherList);
        newWeather.setCity(city);


        return city;
    }

    public WeatherDto convertWeatherToDto(Weather weather){

        String timestamp = weather.getDateTime();
        long timestampLong = Long.parseLong(timestamp);
        Instant instant = Instant.ofEpochSecond(timestampLong);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate givenDate = dateTime.toLocalDate();

        String description = weather.getDescription();
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setDescription(description.substring(0,1).toUpperCase() + description.substring(1).toLowerCase());
        weatherDto.setDateTime(givenDate);
        weatherDto.setHumidity(weather.getHumidity());
        weatherDto.setWindSpeed(weather.getWindSpeed());
        weatherDto.setFeelsLikeTemperature(weather.getFeelsLikeTemperature());
        weatherDto.setTemperature(weather.getTemperature());
        weatherDto.setCity(weather.getCity().getName());

        return weatherDto;
    }



}


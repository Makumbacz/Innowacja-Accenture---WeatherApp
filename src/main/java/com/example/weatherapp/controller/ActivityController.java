package com.example.weatherapp.controller;

import com.example.weatherapp.model.Activity;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.service.ActivityService;
import com.example.weatherapp.service.CityService;
import com.example.weatherapp.service.ForecastService;
import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@Slf4j
@Transactional
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private static final String API_KEY = "b07fa6c18655a358b7c1efd1e97a5386";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?appid=" + API_KEY + "&units=metric";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?appid=" + API_KEY + "&units=metric";
    private final WeatherService weatherService;
    private final ActivityService activityService;



    @GetMapping("/suggestion")
    public ResponseEntity<Object> suggestActivity(@RequestParam String city) {
        RestTemplate restTemplate = new RestTemplate();

        String CITY = "&q="+city;
        ResponseEntity<String> response = restTemplate.exchange(
                WEATHER_URL + CITY,
                HttpMethod.GET,
                null,
                String.class);
        JSONObject json = new JSONObject(response.getBody());
        log.info(json.toString());
        Weather weather = weatherService.getNewWeatherForCity(json);
        List<Activity> activities = activityService.getAllActivities();
        List<Activity> suitableActivities = new ArrayList<>();
            for (Activity activity : activities) {
                if (activity.isSuitable(weather)) {
                    suitableActivities.add(activity);
                }
            }

        if(suitableActivities.isEmpty())
            return ResponseEntity.ok().body("Failed");
        return ResponseEntity.ok().body(suitableActivities);
    }
}





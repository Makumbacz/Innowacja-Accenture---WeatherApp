package com.example.weatherapp.controller;

import com.example.weatherapp.model.Activity;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.service.ActivityService;
import com.example.weatherapp.service.CityService;
import com.example.weatherapp.service.ForecastService;
import com.example.weatherapp.service.WeatherService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RestController
@Slf4j
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private static final String API_KEY = "47cb2bfedd608e78977de1c27689d50e";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?appid=" + API_KEY + "&units=metric";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?appid=" + API_KEY + "&units=metric";
    private final WeatherService weatherService;
    private final ActivityService activityService;


    @GetMapping("/{id}")
    public ResponseEntity<Object>getSuggestionById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activityService.getActivityById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin!";
    }

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





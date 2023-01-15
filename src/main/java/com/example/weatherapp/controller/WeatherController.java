package com.example.weatherapp.controller;


import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.service.CityService;
import com.example.weatherapp.service.ForecastService;
import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private static final String API_KEY = "b07fa6c18655a358b7c1efd1e97a5386";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?appid=" + API_KEY + "&units=metric";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?appid=" + API_KEY + "&units=metric";
    private static String CITY = "&q=warsaw";
    private final WeatherService weatherService;
    private final CityService cityService;
    private final ForecastService forecastService;


    @GetMapping("/current")
    public ResponseEntity<Weather> getCurrentWeather(@RequestParam("city") String city) {
        CITY = "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();

        // Make a GET request to the OpenWeatherMap API using the city name as a query parameter
        ResponseEntity<String> response = restTemplate.exchange(
                WEATHER_URL + CITY,
                HttpMethod.GET,
                null,
                String.class);

        JSONObject json = new JSONObject(response.getBody());
        log.info(json.toString());
        return ResponseEntity.ok().body(weatherService.getNewWeatherForCity(json));
    }

    //TODO: forecast endpoint
    @GetMapping("/forecast")
    public ResponseEntity<Forecast> getForecast(@RequestParam("city") String city) {
        CITY = "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();

        // Make a GET request to the OpenWeatherMap API using the city name as a query parameter
        ResponseEntity<String> response = restTemplate.exchange(
                FORECAST_URL + CITY,
                HttpMethod.GET,
                null,
                String.class);
        JSONObject json = new JSONObject(response.getBody());
        log.info(json.toString());
        return ResponseEntity.ok().body(forecastService.getNewForecastForCity(json));
    }
}

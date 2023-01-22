package com.example.weatherapp.controller;


import com.example.weatherapp.dto.WeatherDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
@CrossOrigin(origins = "http://127.0.0.1:5173")

public class WeatherController {

    private static final String API_KEY = "b07fa6c18655a358b7c1efd1e97a5386";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?appid=" + API_KEY + "&units=metric";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?appid=" + API_KEY + "&units=metric";
    private static String CITY = "&q=warsaw";
    private final WeatherService weatherService;
    private final CityService cityService;
    private final ForecastService forecastService;

    @GetMapping("/current")
    public ResponseEntity<Weather> getCurrentWeatherCity(@RequestParam("city") String city) {
        CITY = "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                WEATHER_URL + CITY,
                HttpMethod.GET,
                null,
                String.class);

        JSONObject json = new JSONObject(response.getBody());
        log.info(json.toString());

        return ResponseEntity.ok().body(weatherService.getNewWeatherForCity(json));
    }
    @GetMapping("/current/cords")
    public ResponseEntity<WeatherDto> getCurrentWeatherLatitudeLongitude(@RequestParam double latitude, @RequestParam double longitude) {
        CITY = "&lat=" + latitude + "&lon=" + longitude;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    WEATHER_URL + CITY,
                    HttpMethod.GET,
                    null,
                    String.class);

            JSONObject json = new JSONObject(response.getBody());
            log.info(json.toString());
            Weather weather = weatherService.getNewWeatherForCity(json);
            WeatherDto weatherDto = weatherService.convertWeatherToDto(weather);
            return ResponseEntity.ok().body(weatherDto);
        } catch (RestClientException e) {
            log.error("Error" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forecast")
    public ResponseEntity<Object> getForecast(@RequestParam("city") String city) {
        CITY = "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                FORECAST_URL + CITY,
                HttpMethod.GET,
                null,
                String.class);
        JSONObject json = new JSONObject(response.getBody());
        log.info(json.toString());
        return ResponseEntity.ok().body(forecastService.convertForecastToDtos(forecastService.getNewForecastForCity(json)));
    }
}

package com.example.weatherapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDto {
    private int temperature;
    private int windSpeed;
    private int humidity;
    private String description;
    private LocalDate dateTime;
    private int feelsLikeTemperature;
    private String city;
}

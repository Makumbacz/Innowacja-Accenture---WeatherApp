package com.example.weatherapp.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ForecastDto {

    private double todayTopTemperature;
    private double todayLowestTemperature;
    private double windSpeed;
    private double humidity;
    private LocalDate date;
    private String description;



}

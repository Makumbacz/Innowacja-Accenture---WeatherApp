package com.example.weatherapp.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum WeatherDescription {
    CLEAR("Clear"),
    CLOUDS("Clouds"),
    SMOKE("Smoke"),
    FOG("Fog"),
    SHOWER_RAIN("shower rain"),
    RAIN("Rain"),
    THUNDERSTORM("Thunderstorm"),
    SNOW("Snow"),
    MIST("Mist");

    private String weatherDescription;

    WeatherDescription(String description) {
        this.weatherDescription = description;
    }

}

package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Map;

@Entity
@Table(name = "activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String name;
    private String description;
    @ElementCollection
    Map<Integer, String> weatherTypes;
    private double temperatureMin;
    private double temperatureMax;
    private double windSpeedMin;
    private double windSpeedMax;
    private double humidityMin;
    private double humidityMax;
    private double feelsLikeTemperatureMin;
    private double feelsLikeTemperatureMax;
    private ActivityType type;

    public Activity(String name, ActivityType type) {
        this.name = name;
        this.type = type;
    }


    public boolean isSuitable(Weather weather) {
        if (this.weatherTypes.containsValue(weather.getDescription())) {
            double temperature = weather.getTemperature();
            double windSpeed = weather.getWindSpeed();
            double humidity = weather.getHumidity();
            double feelsLikeTemperature = weather.getFeelsLikeTemperature();
            return this.temperatureMin <= temperature && temperature <= this.temperatureMax
                    && this.windSpeedMin <= windSpeed && windSpeed <= this.windSpeedMax
                    && this.humidityMin <= humidity && humidity <= this.humidityMax
                    && this.feelsLikeTemperatureMin <= feelsLikeTemperature && feelsLikeTemperature <= this.feelsLikeTemperatureMax;
        }
        return false;
    }
}


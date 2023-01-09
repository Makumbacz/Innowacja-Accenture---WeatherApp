package com.example.weatherapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "weather")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Weather {
    @Id
    private String id;
    private int temperature;
    private int windSpeed;
    private int humidity;
    private int uvIndex;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Weather(int temperature, int windSpeed, int humidity, int uvIndex, City city) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.uvIndex = uvIndex;
        this.city = city;
    }
}
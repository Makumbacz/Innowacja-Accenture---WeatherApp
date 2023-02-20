package com.example.weatherapp.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

@Entity
@Table(name = "weather")
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@RequiredArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int temperature;
    private int windSpeed;
    private int humidity;
    private String description;
    private String dateTime;
    private int feelsLikeTemperature;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Forecast forecast;

    public Weather(int temperature, int windSpeed, int humidity, String description, City city) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
        this.city = city;
    }
    public Weather setWeatherFromJsonObject(JSONObject jsonObject) {
        this.setHumidity((int) ((JSONObject) jsonObject.get("main")).get("humidity"));
        this.setTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("temp").toString())));
        this.setDateTime(jsonObject.get("dt").toString());
        this.setDescription(((JSONObject) ((JSONArray) jsonObject.get("weather")).get(0)).get("main").toString().toUpperCase());
        this.setWindSpeed((int) Math.ceil( Double.parseDouble((((JSONObject) jsonObject.get("wind")).get("speed").toString()))));
        this.setFeelsLikeTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("feels_like").toString())));
        return this;
    }
}
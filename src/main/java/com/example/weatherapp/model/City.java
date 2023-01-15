package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Table(name = "cities")
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@RequiredArgsConstructor
public class City {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")

    private String id;
    private String name;
    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Weather weather;

    public City(String cityName, double latitude, double longitude, Weather weather) {
        this.name = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.weather = weather;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return id != null && Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        weather.setCity(this);
    }
}
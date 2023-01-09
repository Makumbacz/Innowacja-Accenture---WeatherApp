package com.example.weatherapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "cities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class City {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "city")
    private Weather weather;


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

}
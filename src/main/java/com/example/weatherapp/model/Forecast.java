package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

@Table(name = "forecast")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Forecast {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @OneToMany(mappedBy = "forecast", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Weather> weathers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Forecast forecast = (Forecast) o;
        return id != null && Objects.equals(id, forecast.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setForecastFromJsonObject(JSONObject jsonObject) {



    /*    this.setHumidity((int) ((JSONObject) jsonObject.get("main")).get("humidity"));
        this.setTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("temp").toString())));
        this.setDateTime(jsonObject.get("dt").toString());
        this.setDescription(((JSONObject) ((JSONArray) jsonObject.get("weather")).get(0)).get("description").toString());
        this.setWindSpeed((int) Math.ceil( Double.parseDouble((((JSONObject) jsonObject.get("wind")).get("speed").toString()))));
        this.setFeelsLikeTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("feels_like").toString())));
*/    }
}

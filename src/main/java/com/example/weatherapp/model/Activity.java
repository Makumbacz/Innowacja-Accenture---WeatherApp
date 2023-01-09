package com.example.weatherapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Activity {
    @Id
    private String id;
    private String name;
    private ActivityType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Activity activity = (Activity) o;
        return id != null && Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Activity(String name, ActivityType type) {
        this.name = name;
        this.type = type;
    }
}


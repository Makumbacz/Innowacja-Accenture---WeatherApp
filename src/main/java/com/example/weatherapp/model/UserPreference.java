package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserPreference {
    @Id
    private String id;

    private ActivityType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPreference that = (UserPreference) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UserPreference(ActivityType type, User user) {
        this.type = type;
        this.user = user;
    }
}
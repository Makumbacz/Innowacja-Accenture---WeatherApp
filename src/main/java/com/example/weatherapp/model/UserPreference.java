package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ActivityType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserPreference(ActivityType type, User user) {
        this.type = type;
        this.user = user;
    }

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
}
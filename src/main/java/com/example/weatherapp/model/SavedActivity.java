package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "saved_activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SavedActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ActivityType type;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

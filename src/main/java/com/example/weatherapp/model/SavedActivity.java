package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "saved_activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SavedActivity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private ActivityType type;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

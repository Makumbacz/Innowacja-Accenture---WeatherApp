package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saved_activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SavedActivity {
    @Id
    private String id;

    private String name;
    private ActivityType type;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

package com.example.weatherapp.repository;

import com.example.weatherapp.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}

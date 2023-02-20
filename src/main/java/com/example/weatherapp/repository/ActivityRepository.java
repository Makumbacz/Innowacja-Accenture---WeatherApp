package com.example.weatherapp.repository;

import com.example.weatherapp.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

   Activity findActivityById(Long id);
}

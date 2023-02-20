package com.example.weatherapp.service;

import com.example.weatherapp.model.Activity;
import com.example.weatherapp.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
    public Activity getActivityById(Long id) {
        return activityRepository.findActivityById(id);
    }


}

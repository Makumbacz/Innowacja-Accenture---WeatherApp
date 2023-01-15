package com.example.weatherapp.config;

import com.example.weatherapp.model.Activity;
import com.example.weatherapp.model.ActivityType;
import com.example.weatherapp.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    CommandLineRunner run(ActivityRepository activityRepository) {

        return args -> {
            Activity hiking = new Activity();
            hiking.setName("Hiking");
            hiking.setDescription("Explore nature and enjoy the beautiful scenery");
            Map<Integer, String> hikingWeatherTypes = new HashMap<>();
            hikingWeatherTypes.put(1, "clear sky");
            hikingWeatherTypes.put(2, "sunny");
            hikingWeatherTypes.put(3, "few clouds");
            hiking.setWeatherTypes(hikingWeatherTypes);
            hiking.setTemperatureMin(10);
            hiking.setTemperatureMax(25);
            hiking.setWindSpeedMin(0);
            hiking.setWindSpeedMax(20);
            hiking.setHumidityMin(30);
            hiking.setHumidityMax(70);
            hiking.setType(ActivityType.OUTDOOR);
            hiking.setFeelsLikeTemperatureMin(10);
            hiking.setFeelsLikeTemperatureMax(25);
            activityRepository.save(hiking);

            Activity swimming = new Activity();
            swimming.setName("Swimming");
            swimming.setDescription("Enjoy the warm weather and cool off in the pool");
            Map<Integer, String> swimmingWeatherTypes = new HashMap<>();
            swimmingWeatherTypes.put(1, "clear sky");
            swimmingWeatherTypes.put(2, "sunny");
            swimming.setWeatherTypes(swimmingWeatherTypes);
            swimming.setTemperatureMin(20);
            swimming.setTemperatureMax(35);
            swimming.setWindSpeedMin(0);
            swimming.setWindSpeedMax(5);
            swimming.setHumidityMin(30);
            swimming.setHumidityMax(70);
            swimming.setType(ActivityType.INDOOR);
            swimming.setFeelsLikeTemperatureMin(20);
            swimming.setFeelsLikeTemperatureMax(35);
            activityRepository.save(swimming);

            Activity biking = new Activity();
            biking.setName("Biking");
            biking.setDescription("Get some exercise and enjoy the outdoors");
            Map<Integer, String> bikingWeatherTypes = new HashMap<>();
            bikingWeatherTypes.put(1, "clear sky");
            bikingWeatherTypes.put(2, "sunny");
            bikingWeatherTypes.put(3, "few clouds");
            bikingWeatherTypes.put(4, "scattered clouds");
            biking.setWeatherTypes(bikingWeatherTypes);
            biking.setTemperatureMin(15);
            biking.setTemperatureMax(30);
            biking.setWindSpeedMin(0);
            biking.setWindSpeedMax(15);
            biking.setHumidityMin(40);
            biking.setHumidityMax(60);
            biking.setType(ActivityType.OUTDOOR);
            biking.setFeelsLikeTemperatureMin(15);
            biking.setFeelsLikeTemperatureMax(30);
            activityRepository.save(biking);
        };
    }
}

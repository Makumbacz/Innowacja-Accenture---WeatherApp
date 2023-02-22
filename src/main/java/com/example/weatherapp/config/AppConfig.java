package com.example.weatherapp.config;

import com.example.weatherapp.model.*;
import com.example.weatherapp.repository.ActivityRepository;
import com.example.weatherapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner run(ActivityRepository activityRepository, UserService userService, PasswordEncoder passwordEncoder) {

        return args -> {

            ArrayList<Role> roles = new ArrayList<>();
            roles.add(userService.createRoleIfNotFound("READ"));
            roles.add(userService.createRoleIfNotFound("WRITE"));
            roles.add(userService.createRoleIfNotFound("DELETE"));

            User user = new User();
            user.setUsername("Admin");
            user.setEmail("admin@gmail.com");
            user.setPassword("Admin");
            user.setAuthorities(List.of(roles.get(0),roles.get(1),roles.get(2)));
            userService.addNewUser(user);

            user = new User();
            user.setUsername("Arek123");
            user.setEmail("awietess@gmail.com");
            user.setPassword("Arek123");
            user.setAuthorities(List.of(roles.get(0)));
            userService.addNewUser(user);

            Activity hiking = new Activity();
            hiking.setName("Hiking");
            hiking.setDescription("Explore nature and enjoy the beautiful scenery");
            Map<Integer, WeatherDescription> hikingWeatherTypes = new HashMap<>();
            hikingWeatherTypes.put(1, WeatherDescription.CLEAR);
            hikingWeatherTypes.put(2, WeatherDescription.SMOKE);
            hikingWeatherTypes.put(3, WeatherDescription.CLOUDS);
            hikingWeatherTypes.put(4, WeatherDescription.FOG);
            hiking.setWeatherTypes(hikingWeatherTypes);
            hiking.setTemperatureMin(10);
            hiking.setTemperatureMax(25);
            hiking.setWindSpeedMin(0);
            hiking.setWindSpeedMax(20);
            hiking.setHumidityMin(30);
            hiking.setHumidityMax(120);
            hiking.setType(ActivityType.OUTDOOR);
            hiking.setFeelsLikeTemperatureMin(10);
            hiking.setFeelsLikeTemperatureMax(25);
            activityRepository.save(hiking);

            Activity swimming = new Activity();
            swimming.setName("Swimming");
            swimming.setDescription("Enjoy the warm weather and cool off in the pool");
            Map<Integer, WeatherDescription> swimmingWeatherTypes = new HashMap<>();
            swimmingWeatherTypes.put(1, WeatherDescription.CLEAR);
            swimmingWeatherTypes.put(2, WeatherDescription.CLOUDS);
            swimming.setWeatherTypes(swimmingWeatherTypes);
            swimming.setTemperatureMin(20);
            swimming.setTemperatureMax(35);
            swimming.setWindSpeedMin(0);
            swimming.setWindSpeedMax(5);
            swimming.setHumidityMin(30);
            swimming.setHumidityMax(120);
            swimming.setType(ActivityType.INDOOR);
            swimming.setFeelsLikeTemperatureMin(20);
            swimming.setFeelsLikeTemperatureMax(35);
            activityRepository.save(swimming);

            Activity skating = new Activity();
            skating.setName("Skating");
            skating.setDescription("Whether it's ice-skating on a frozen lake or rink, or roller-skating on an indoor rink, skating is a fun way to stay active in the colder months.");
            Map<Integer, WeatherDescription> skatingWeatherTypes = new HashMap<>();
            skatingWeatherTypes.put(1, WeatherDescription.SNOW);
            skatingWeatherTypes.put(5, WeatherDescription.CLEAR);
            skatingWeatherTypes.put(2, WeatherDescription.SMOKE);
            skatingWeatherTypes.put(3, WeatherDescription.CLOUDS);
            skatingWeatherTypes.put(4, WeatherDescription.FOG);
            skating.setWeatherTypes(skatingWeatherTypes);
            skating.setTemperatureMin(-10);
            skating.setTemperatureMax(7);
            skating.setWindSpeedMin(0);
            skating.setWindSpeedMax(5);
            skating.setHumidityMin(20);
            skating.setHumidityMax(120);
            skating.setFeelsLikeTemperatureMin(-10);
            skating.setFeelsLikeTemperatureMax(7);
            activityRepository.save(skating);

            Activity biking = new Activity();
            biking.setName("Biking");
            biking.setDescription("Get some exercise and enjoy the outdoors");
            Map<Integer, WeatherDescription> bikingWeatherTypes = new HashMap<>();
            bikingWeatherTypes.put(1, WeatherDescription.CLEAR);
            bikingWeatherTypes.put(2, WeatherDescription.SMOKE);
            bikingWeatherTypes.put(3, WeatherDescription.CLOUDS);
            bikingWeatherTypes.put(4, WeatherDescription.FOG);
            biking.setWeatherTypes(bikingWeatherTypes);
            biking.setTemperatureMin(13);
            biking.setTemperatureMax(30);
            biking.setWindSpeedMin(0);
            biking.setWindSpeedMax(15);
            biking.setHumidityMin(40);
            biking.setHumidityMax(120);
            biking.setType(ActivityType.OUTDOOR);
            biking.setFeelsLikeTemperatureMin(13);
            biking.setFeelsLikeTemperatureMax(30);
            activityRepository.save(biking);

            Activity indoorPools = new Activity();
            indoorPools.setName("Indoor Pools");
            indoorPools.setDescription("Many cities have indoor pools where you can swim laps or take a leisurely swim. It's a great way to stay active and healthy during the winter months.");
            Map<Integer, WeatherDescription> indoorPoolsWeatherTypes = new HashMap<>();
            indoorPoolsWeatherTypes.put(1, WeatherDescription.CLEAR);
            indoorPoolsWeatherTypes.put(2, WeatherDescription.CLOUDS);
            indoorPoolsWeatherTypes.put(3, WeatherDescription.SMOKE);
            indoorPoolsWeatherTypes.put(4, WeatherDescription.FOG);
            indoorPoolsWeatherTypes.put(5, WeatherDescription.SHOWER_RAIN);
            indoorPoolsWeatherTypes.put(6, WeatherDescription.RAIN);
            indoorPoolsWeatherTypes.put(7, WeatherDescription.THUNDERSTORM);
            indoorPoolsWeatherTypes.put(8, WeatherDescription.SNOW);
            indoorPoolsWeatherTypes.put(9, WeatherDescription.MIST);
            indoorPools.setWeatherTypes(indoorPoolsWeatherTypes);
            indoorPools.setTemperatureMin(-20);
            indoorPools.setTemperatureMax(35);
            indoorPools.setWindSpeedMin(0);
            indoorPools.setWindSpeedMax(10);
            indoorPools.setHumidityMin(20);
            indoorPools.setHumidityMax(100);
            indoorPools.setFeelsLikeTemperatureMin(-20);
            indoorPools.setFeelsLikeTemperatureMax(35);
            activityRepository.save(indoorPools);

            Activity gym = new Activity();
            gym.setName("Gym");
            gym.setDescription("Many gyms and fitness centers offer a wide variety of indoor fitness classes, such as yoga, Pilates, and aerobics. They can be a great way to stay active and healthy during the winter months.");
            Map<Integer, WeatherDescription> gymWeatherTypes = new HashMap<>();
            gymWeatherTypes.put(1, WeatherDescription.CLEAR);
            gymWeatherTypes.put(2, WeatherDescription.CLOUDS);
            gymWeatherTypes.put(3, WeatherDescription.SMOKE);
            gymWeatherTypes.put(4, WeatherDescription.FOG);
            gymWeatherTypes.put(5, WeatherDescription.SHOWER_RAIN);
            gymWeatherTypes.put(6, WeatherDescription.RAIN);
            gymWeatherTypes.put(7, WeatherDescription.THUNDERSTORM);
            gymWeatherTypes.put(8, WeatherDescription.SNOW);
            gymWeatherTypes.put(9, WeatherDescription.MIST);
            gym.setWeatherTypes(gymWeatherTypes);
            gym.setTemperatureMin(-20);
            gym.setTemperatureMax(35);
            gym.setWindSpeedMin(0);
            gym.setWindSpeedMax(10);
            gym.setHumidityMin(20);
            gym.setHumidityMax(100);
            gym.setFeelsLikeTemperatureMin(-20);
            gym.setFeelsLikeTemperatureMax(35);
            activityRepository.save(gym);

        };
    }
}

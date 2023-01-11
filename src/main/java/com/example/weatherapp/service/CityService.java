package com.example.weatherapp.service;

import com.example.weatherapp.model.City;
import com.example.weatherapp.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public City saveCity(City city){
        City cityFound = cityRepository.findByName(city.getName());
        if(cityFound != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City already exists.");
        }
        cityRepository.save(city);
        return city;
    }
    public City saveCity(String name, Double lon, Double lat){
        City city = new City();
        city.setName(name);
        city.setLongitude(lon);
        city.setLongitude(lat);

        return city;
    }

}

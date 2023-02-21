package com.example.weatherapp;

import com.example.weatherapp.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}

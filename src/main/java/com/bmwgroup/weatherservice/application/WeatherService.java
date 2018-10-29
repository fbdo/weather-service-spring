package com.bmwgroup.weatherservice.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherProviderClient weatherProviderClient;

    public WeatherData current(Optional<String> cityName) {
        WeatherProviderForecast forecast = weatherProviderClient.current(cityName);
        return new WeatherData(forecast.getTemperature(), forecast.getDescription());
    }
}

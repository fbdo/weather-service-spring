package com.bmwgroup.weatherservice.ports.restapi;

import com.bmwgroup.weatherservice.application.WeatherData;
import com.bmwgroup.weatherservice.application.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/current")
    public WeatherData current(@RequestParam(value = "city", defaultValue = "Munich") String cityName) {
        return weatherService.current(Optional.of(cityName));
    }
}

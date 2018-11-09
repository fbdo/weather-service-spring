package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.bmwgroup.weatherservice.application.WeatherProviderClient;
import com.bmwgroup.weatherservice.application.WeatherProviderForecast;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("reliable")
public class RealiableWeatherProviderClient implements WeatherProviderClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealiableWeatherProviderClient.class);

    @Autowired
    @Qualifier("openweathermap")
    private WeatherProviderClient decorated;

    @Value("${openweathermap.url}")
    private String url;


    @Override
    @HystrixCommand(fallbackMethod = "reliableCurrent")
    public WeatherProviderForecast current(Optional<String> cityName) {
        return decorated.current(cityName);
    }

    public WeatherProviderForecast reliableCurrent(Optional<String> cityName) {
        LOGGER.warn(String.format("Access to weather service url %s failed, returning reliable response", url));

        return new WeatherProviderForecast(cityName.orElse("Munich"), -273.15, "Unknown");
    }


}

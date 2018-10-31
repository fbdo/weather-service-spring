package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.bmwgroup.weatherservice.application.WeatherProviderClient;
import com.bmwgroup.weatherservice.application.WeatherProviderForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("cached")
@Primary
public class CachedWeatherProviderClient implements WeatherProviderClient {


    private WeatherProviderClient decorated;

    private static final Logger LOGGER = LoggerFactory.getLogger(CachedWeatherProviderClient.class);

    public CachedWeatherProviderClient(@Autowired @Qualifier( "openweathermap") WeatherProviderClient decorated) {
        this.decorated = decorated;
    }

    @Override
    @Cacheable(cacheNames = "currentWeatherPerCity", key="#cityName.get()")
    public WeatherProviderForecast current(Optional<String> cityName) {
        LOGGER.debug("Not using cached version, calling the decorated client instead");

        return decorated.current(cityName);
    }
}

package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.bmwgroup.weatherservice.application.WeatherProviderClient;
import com.bmwgroup.weatherservice.application.WeatherProviderForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Qualifier( "openweathermap")
public class OpenWeatherMapClient implements WeatherProviderClient {

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Value("${openweathermap.url}")
    private String url;

    private String defaultCity = "Munich";

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    @Override
    public WeatherProviderForecast current(Optional<String> cityName) {
        RestTemplate restTemplate = new RestTemplate();

        LOGGER.debug("OpenWeatherMap URL: {}", url);

        OpenWeatherMapResponse resp = restTemplate.getForObject(String.format(url, cityName.orElse(defaultCity)), OpenWeatherMapResponse.class);

        if (resp.getCities().length > 0) {
            return new WeatherProviderForecast(resp.getCities()[0].getName(), resp.getCities()[0].getMainForecast().getTemperature(), resp.getCities()[0].getWeather()[0].getDescription());
        }
        throw new IllegalArgumentException(cityName.get());
    }
}
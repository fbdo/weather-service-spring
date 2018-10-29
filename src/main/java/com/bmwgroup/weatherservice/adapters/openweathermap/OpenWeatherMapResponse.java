package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherMapResponse {

    @JsonProperty("list")
    private CityWeatherForecast[] cities;

    public CityWeatherForecast[] getCities() {
        return cities;
    }
}

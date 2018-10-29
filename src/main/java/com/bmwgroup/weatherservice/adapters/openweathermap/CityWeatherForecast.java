package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityWeatherForecast {
    private String name;

    @JsonProperty("main")
    private CityWeatherMainForecast mainForecast;

    @JsonProperty("weather")
    private WeatherDetails[] weather;

    public String getName() {
        return name;
    }

    public CityWeatherMainForecast getMainForecast() {
        return mainForecast;
    }

    public WeatherDetails[] getWeather() {
        return weather;
    }
}

package com.bmwgroup.weatherservice.adapters.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityWeatherMainForecast {
    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("temp_min")
    private double minTemperature;

    @JsonProperty("temp_max")
    private double maxTemperature;

    public double getTemperature() {
        return temperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}

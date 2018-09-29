package com.svf.edu.dto.builder;

import com.svf.edu.dto.WeatherValue;

/**
 * Created by stepanferubko
 */
public class WeatherValueBuilder {
    private String tempMin;//мінімальна температура
    private String tempMax;//максимальна температура'
    private String precipitation;//опади
    private String windSpeed;//швидкість вітру
    private String pressure;//тиск
    private String humidity;//вологість повітря
    private boolean hasError;

    private WeatherValueBuilder() {
    }

    public WeatherValueBuilder setTempMin(String tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public WeatherValueBuilder setTempMax(String tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public WeatherValueBuilder setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
        return this;
    }

    public WeatherValueBuilder setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public WeatherValueBuilder setPressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    public WeatherValueBuilder setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public WeatherValueBuilder setHasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public WeatherValue build() {
        WeatherValue weatherValue = new WeatherValue();

        weatherValue.setTempMin(tempMin);
        weatherValue.setTempMax(tempMax);
        weatherValue.setPrecipitation(precipitation);
        weatherValue.setWindSpeed(windSpeed);
        weatherValue.setPressure(pressure);
        weatherValue.setHumidity(humidity);
        weatherValue.setHasError(hasError);
        return weatherValue;
    }

    public static WeatherValueBuilder builder() {
        return new WeatherValueBuilder();
    }
}

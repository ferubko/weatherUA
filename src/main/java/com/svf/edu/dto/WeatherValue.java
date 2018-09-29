package com.svf.edu.dto;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class WeatherValue implements Serializable {
    private String tempMin;//мінімальна температура
    private String tempMax;//максимальна температура'
    private String precipitation;//опади
    private String windSpeed;//швидкість вітру
    private String pressure;//тиск
    private String humidity;//вологість повітря

    private boolean hasError;

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherValue that = (WeatherValue) o;

        if (hasError != that.hasError) return false;
        if (windSpeed != null ? !windSpeed.equals(that.windSpeed) : that.windSpeed != null) return false;
        if (precipitation != null ? !precipitation.equals(that.precipitation) : that.precipitation != null)
            return false;
        if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) return false;
        if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null) return false;
        if (tempMin != null ? !tempMin.equals(that.tempMin) : that.tempMin != null) return false;
        return tempMax != null ? tempMax.equals(that.tempMax) : that.tempMax == null;

    }

    @Override
    public int hashCode() {
        int result = windSpeed != null ? windSpeed.hashCode() : 0;
        result = 31 * result + (precipitation != null ? precipitation.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (tempMin != null ? tempMin.hashCode() : 0);
        result = 31 * result + (tempMax != null ? tempMax.hashCode() : 0);
        result = 31 * result + (hasError ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherValue{" +
                "windSpeed='" + windSpeed + '\'' +
                ", precipitation='" + precipitation + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", hasError=" + hasError +
                '}';
    }
}

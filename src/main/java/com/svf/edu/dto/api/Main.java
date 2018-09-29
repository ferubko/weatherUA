package com.svf.edu.dto.api;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class Main implements Serializable {
    private String humidity;

    private String pressure;

    private String temp_max;

    private String sea_level;

    private String temp_min;

    private String temp;

    private String grnd_level;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getSea_level() {
        return sea_level;
    }

    public void setSea_level(String sea_level) {
        this.sea_level = sea_level;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(String grnd_level) {
        this.grnd_level = grnd_level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (humidity != null ? !humidity.equals(main.humidity) : main.humidity != null) return false;
        if (pressure != null ? !pressure.equals(main.pressure) : main.pressure != null) return false;
        if (temp_max != null ? !temp_max.equals(main.temp_max) : main.temp_max != null) return false;
        if (sea_level != null ? !sea_level.equals(main.sea_level) : main.sea_level != null) return false;
        if (temp_min != null ? !temp_min.equals(main.temp_min) : main.temp_min != null) return false;
        if (temp != null ? !temp.equals(main.temp) : main.temp != null) return false;
        return grnd_level != null ? grnd_level.equals(main.grnd_level) : main.grnd_level == null;

    }

    @Override
    public int hashCode() {
        int result = humidity != null ? humidity.hashCode() : 0;
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (temp_max != null ? temp_max.hashCode() : 0);
        result = 31 * result + (sea_level != null ? sea_level.hashCode() : 0);
        result = 31 * result + (temp_min != null ? temp_min.hashCode() : 0);
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (grnd_level != null ? grnd_level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Main{" +
                "humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", temp_max='" + temp_max + '\'' +
                ", sea_level='" + sea_level + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp='" + temp + '\'' +
                ", grnd_level='" + grnd_level + '\'' +
                '}';
    }
}

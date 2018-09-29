package com.svf.edu.dto.api;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class Sys implements Serializable {
    private String message;

    private String sunset;

    private String sunrise;

    private String country;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (message != null ? !message.equals(sys.message) : sys.message != null) return false;
        if (sunset != null ? !sunset.equals(sys.sunset) : sys.sunset != null) return false;
        if (sunrise != null ? !sunrise.equals(sys.sunrise) : sys.sunrise != null) return false;
        return country != null ? country.equals(sys.country) : sys.country == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (sunset != null ? sunset.hashCode() : 0);
        result = 31 * result + (sunrise != null ? sunrise.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "message='" + message + '\'' +
                ", sunset='" + sunset + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

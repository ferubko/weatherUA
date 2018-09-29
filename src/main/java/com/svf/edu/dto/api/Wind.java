package com.svf.edu.dto.api;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class Wind implements Serializable {
    private String speed;

    private String deg;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (speed != null ? !speed.equals(wind.speed) : wind.speed != null) return false;
        return deg != null ? deg.equals(wind.deg) : wind.deg == null;

    }

    @Override
    public int hashCode() {
        int result = speed != null ? speed.hashCode() : 0;
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed='" + speed + '\'' +
                ", deg='" + deg + '\'' +
                '}';
    }
}

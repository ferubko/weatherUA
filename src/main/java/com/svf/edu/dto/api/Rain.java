package com.svf.edu.dto.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class Rain implements Serializable {
    @SerializedName("3h")//optional
    private String perHour;

    public String getPerHour() {
        return perHour;
    }

    public void setPerHour(String perHour) {
        this.perHour = perHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rain rain = (Rain) o;

        return perHour != null ? perHour.equals(rain.perHour) : rain.perHour == null;
    }

    @Override
    public int hashCode() {
        return perHour != null ? perHour.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "perHour='" + perHour + '\'' +
                '}';
    }
}

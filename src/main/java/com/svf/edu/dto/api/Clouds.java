package com.svf.edu.dto.api;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class Clouds implements Serializable {
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return all != null ? all.equals(clouds.all) : clouds.all == null;

    }

    @Override
    public int hashCode() {
        return all != null ? all.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all='" + all + '\'' +
                '}';
    }
}

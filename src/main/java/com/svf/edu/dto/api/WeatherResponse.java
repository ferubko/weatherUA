package com.svf.edu.dto.api;

import java.util.List;

/**
 * Created by stepanferubko
 */
public class WeatherResponse extends AbstractResponse {
    private String id;

    private String dt;

    private Clouds clouds;

    private Coord coord;

    private Wind wind;

    private String cod;

    private Sys sys;

    private String name;

    private String base;

    private List<Weather> weather;

    private Rain rain;

    private Main main;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse that = (WeatherResponse) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (coord != null ? !coord.equals(that.coord) : that.coord != null) return false;
        if (wind != null ? !wind.equals(that.wind) : that.wind != null) return false;
        if (cod != null ? !cod.equals(that.cod) : that.cod != null) return false;
        if (sys != null ? !sys.equals(that.sys) : that.sys != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;
        if (rain != null ? !rain.equals(that.rain) : that.rain != null) return false;
        return main != null ? main.equals(that.main) : that.main == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dt != null ? dt.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + (coord != null ? coord.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (cod != null ? cod.hashCode() : 0);
        result = 31 * result + (sys != null ? sys.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (base != null ? base.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "id='" + id + '\'' +
                ", dt='" + dt + '\'' +
                ", clouds=" + clouds +
                ", coord=" + coord +
                ", wind=" + wind +
                ", cod='" + cod + '\'' +
                ", sys=" + sys +
                ", name='" + name + '\'' +
                ", base='" + base + '\'' +
                ", weather=" + weather +
                ", rain=" + rain +
                ", main=" + main +
                '}';
    }
}

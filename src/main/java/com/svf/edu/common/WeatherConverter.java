package com.svf.edu.common;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by stepanferubko
 */
public class WeatherConverter {
    public static final String NO_VALUE = "Немає данних";

    public static String convertTemperature(String temp) {
        if (StringUtils.isBlank(temp))
            return NO_VALUE;
        BigDecimal kelvin = new BigDecimal(temp);
        BigDecimal constant = new BigDecimal("273.15");
        BigDecimal subtract = kelvin.subtract(constant);
        return subtract.toString();
    }

    public static String convertPrecipitation(String mainRainValue) {
        if (StringUtils.isBlank(mainRainValue)) {
            return NO_VALUE;
        }
        switch (mainRainValue) {
            case "Rain":
                return "Дощ";
            case "Snow":
                return "Сніг";
            case "Clouds":
                return "Хмарно";
            case "Clear":
                return "Без опадів";
            default:
                return "-";
        }
    }

    public static String convertValue(String value) {
        if (StringUtils.isBlank(value)) {
            return NO_VALUE;
        }
        return value;
    }
}

/*
{"coord":{"lon":33.48,"lat":46.81},
"weather":[{"id":501,"main":"Rain","description":"moderate rain","icon":"10d"}],
"base":"stations",
"main":{"temp":289.663,"pressure":1017.5,"humidity":100,
"temp_min":289.663,"temp_max":289.663,"sea_level":1022.46,"grnd_level":1017.5},
"wind":{"speed":7.31,"deg":21.5053},"rain":{"3h":3.505},
"clouds":{"all":100},"dt":1536409735,
"sys":{"message":0.0022,"country":"UA","sunrise":1536376504,"sunset":1536423079},
"id":707244,"name":"Kakhovka","cod":200}
 */
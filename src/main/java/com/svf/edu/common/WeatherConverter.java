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

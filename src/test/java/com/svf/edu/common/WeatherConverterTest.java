package com.svf.edu.common;

import org.junit.Test;

/**
 * Created by stepanferubko
 */
public class WeatherConverterTest {
    @Test
    public void temperature() throws Exception {
        String temperature = WeatherConverter.convertTemperature("288.622");
        System.out.println(temperature);
    }
}
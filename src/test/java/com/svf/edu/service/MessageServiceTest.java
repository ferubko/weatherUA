package com.svf.edu.service;


import com.svf.edu.common.CityEnum;
import com.svf.edu.dto.WeatherValue;
import org.junit.Test;

/**
 * Created by stepanferubko
 */
public class MessageServiceTest {
    @Test
    public void combineMessage() throws Exception {
        MessageService messageService = new MessageService();
        String combineMessage = messageService.combineMessage(CityEnum.Mykolayiv, getWeatherValue());
        System.out.println(combineMessage);
    }

    @Test
    public void combineMessage_error() throws Exception {
        MessageService messageService = new MessageService();
        WeatherValue weatherValue = new WeatherValue();
        weatherValue.setHasError(true);
        String combineMessage = messageService.combineMessage(CityEnum.Mykolayiv, weatherValue);
        System.out.println(combineMessage);
    }

    private WeatherValue getWeatherValue() {
        WeatherValue weatherValue = new WeatherValue();
        weatherValue.setTempMin("289.663");
        weatherValue.setTempMax("289.663");
        weatherValue.setPrecipitation("Rain");
        weatherValue.setWindSpeed("7.31");
        weatherValue.setPressure("1017.5");
        weatherValue.setHumidity("100");
        weatherValue.setHasError(false);
        return weatherValue;
    }
}
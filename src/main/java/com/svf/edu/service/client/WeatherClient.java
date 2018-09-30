package com.svf.edu.service.client;

import com.svf.edu.dto.api.WeatherResponse;
import com.svf.edu.exceptions.InternalServiceInvocationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by stepanferubko
 */
//@Component
public class WeatherClient extends AbstractClient {
    public static final String WEATHER_ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?q={q},ua&appid={appid}";

    @Value("${weather.default.key}")
    private String API_KEY;

    public WeatherResponse getWeather(String city) throws InternalServiceInvocationException {
        return getResponse(() -> restTemplate.getForObject(WEATHER_ENDPOINT, WeatherResponse.class, city, API_KEY));

//        WeatherResponse weatherResponse = restTemplate.getForObject(WEATHER_ENDPOINT, WeatherResponse.class, cityOdessa, API_KEY);
//        return new WeatherResponse();
    }
}

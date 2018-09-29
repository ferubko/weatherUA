package com.svf.edu.service.weather;

import com.svf.edu.common.WeatherConverter;
import com.svf.edu.dto.WeatherValue;
import com.svf.edu.dto.api.Main;
import com.svf.edu.dto.api.Weather;
import com.svf.edu.dto.api.WeatherResponse;
import com.svf.edu.dto.api.Wind;
import com.svf.edu.dto.builder.WeatherValueBuilder;
import com.svf.edu.exceptions.InternalServiceInvocationException;
import com.svf.edu.service.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.svf.edu.common.WeatherConverter.convertValue;

/**
 * Created by stepanferubko
 */
@Service
public class WeatherService {
    private final static Logger LOG = Logger.getLogger(WeatherService.class.getName());

    @Autowired
    public WeatherClient weatherClient;
    @Value("${weather.default.key}")
    private String API_KEY;

    public String getName() {
        return API_KEY;
    }

    public WeatherValue getCityWeather(String cityName) {
        WeatherValue weatherValue;
        try {
            WeatherResponse weatherResponse = weatherClient.getWeather(cityName);
            if (!weatherResponse.isError() && weatherResponse.getCod().equals("200")) {
                weatherValue = buildResponse(weatherResponse);
                LOG.log(Level.INFO, "Got weather of " + cityName);
            } else {
                weatherValue = buildFailResponse();
                LOG.log(Level.WARNING, "Error on getting weather of " + cityName);
            }
            return weatherValue;
        } catch (InternalServiceInvocationException e) {
            e.printStackTrace();
            LOG.log(Level.WARNING, "Error on getting weather of " + cityName);
            return buildFailResponse();
        }
    }

    private WeatherValue buildResponse(WeatherResponse weatherResponse) {
        Weather weather = weatherResponse.getWeather().get(0);
        Main main = weatherResponse.getMain();
        Wind wind = weatherResponse.getWind();
        return WeatherValueBuilder.builder()
                .setTempMin(WeatherConverter.convertTemperature(main != null ? main.getTemp_min() : null))
                .setTempMax(WeatherConverter.convertTemperature(main != null ? main.getTemp_max() : null))
                .setPrecipitation(WeatherConverter.convertPrecipitation(weather != null ? weather.getMain() : null))
                .setWindSpeed(wind != null ? convertValue(wind.getSpeed()) : WeatherConverter.NO_VALUE)
                .setPressure(main != null ? convertValue(main.getPressure()) : WeatherConverter.NO_VALUE)
                .setHumidity(main != null ? convertValue(main.getHumidity()) : WeatherConverter.NO_VALUE)
                .setHasError(false)
                .build();
    }

    private WeatherValue buildFailResponse() {
        return WeatherValueBuilder.builder()
                .setTempMin(null)
                .setTempMax(null)
                .setPrecipitation(null)
                .setWindSpeed(null)
                .setPressure(null)
                .setHumidity(null)
                .setHasError(true)
                .build();
    }
}

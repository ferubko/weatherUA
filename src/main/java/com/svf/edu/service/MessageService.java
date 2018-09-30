package com.svf.edu.service;

import com.svf.edu.common.CityEnum;
import com.svf.edu.dto.WeatherValue;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by stepanferubko
 */
@Service
public class MessageService {
    private final static Logger LOG = Logger.getLogger(MessageService.class.getName());

    private String MESSAGE_TEXT = "Ви обрали: %s;\n" +
            "\n" +
            "Мін. температура: %s \u00B0C;\n" +
            "Макс. температура: %s \u00B0C;\n" +
            "Опади: %s;\n" +
            "Вітер: %s М/с;\n" +
            "Тиск: %s;\n" +
            "Вологість: %s;";

    private String ERROR_MESSAGE_TEXT = "Сталася помилка. Будь-ласка повторіть спробу пізніше";


    public String combineMessage(CityEnum city, WeatherValue weatherValue) {
        if (weatherValue.isHasError()) {
            return ERROR_MESSAGE_TEXT;
        } else {
            return String.format(MESSAGE_TEXT,
                    city.getCityName(),
                    weatherValue.getTempMin(),
                    weatherValue.getTempMax(),
                    weatherValue.getPrecipitation(),
                    weatherValue.getWindSpeed(),
                    weatherValue.getPressure(),
                    weatherValue.getHumidity()
            );
        }
    }
}

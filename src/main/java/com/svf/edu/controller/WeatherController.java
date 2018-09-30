package com.svf.edu.controller;

import com.svf.edu.common.CityEnum;
import com.svf.edu.dto.WeatherValue;
import com.svf.edu.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stepanferubko
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
//    @Autowired
//    private MessageService messageService;

    @RequestMapping(path = "/one", method = RequestMethod.GET)
    @ResponseBody
//    public String getWeather() {
    public WeatherValue getWeather() {
        WeatherValue cityWeather = weatherService.getCityWeather(CityEnum.Odessa.getValue());
//        return messageService.combineMessage(CityEnum.Odessa, cityWeather);
        return cityWeather;
    }
}

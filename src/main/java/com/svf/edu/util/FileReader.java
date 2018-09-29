package com.svf.edu.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svf.edu.dto.city.CityResponse;

import java.io.File;
import java.io.IOException;

/**
 * Created by stepanferubko
 */
public class FileReader {
    public static CityResponse read() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        User user = new User();
        CityResponse cityResponse = mapper.readValue(new File("/home/stepanferubko/Desktop/city.list.json"), CityResponse.class);
//        System.out.println(cityResponse.getCity().size());
        return cityResponse;

//Object to JSON in file
//        mapper.writeValue(new File("c:\\user.json"), user);

//Object to JSON in String
//        String jsonInString = mapper.writeValueAsString(user);

    }
}

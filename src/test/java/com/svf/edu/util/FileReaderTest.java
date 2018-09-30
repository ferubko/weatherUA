package com.svf.edu.util;

import com.svf.edu.dto.city.City;
import com.svf.edu.dto.city.CityResponse;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stepanferubko
 */
@Ignore
public class FileReaderTest {
    @Test
    @Ignore
    public void name() throws Exception {
        CityResponse read = FileReader.read();
        List<City> ua = read.getCity().stream().filter(c -> c.getCountry().equals("UA")).collect(Collectors.toList());
        System.out.println(ua.size());
        ua.forEach(c-> System.out.println(c.getName()+"(\"\",\""+c.getName()+"\");"));

    }
}
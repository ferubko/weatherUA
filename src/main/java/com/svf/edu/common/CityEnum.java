package com.svf.edu.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by stepanferubko
 */
public enum CityEnum {
    Vinnytsya("Вінниця", "Vinnytsya"),
    Dniprodzerzhynsk("Дніпро", "Dniprodzerzhynsk"),
    Donetsk("Донецьк", "Donetsk"),
    Zhytomyr("Житомир", "Zhytomyr"),
    Zaporizhzhya("Запоріжжя", "Zaporizhzhya"),
    Ivano_Frankivsk("Івано-Франківськ", "Ivano-Frankivsk"),
    Kirovohrad("Кропивницький", "Kirovohrad"),
    Luhansk("Луганськ", "Luhansk"),
    Lutsk("Луцьк", "Lutsk"),
    Lviv("ЛЬвів", "Lviv"),
    Mykolayiv("Миколаїв", "Mykolayiv"),
    Odessa("Одеса", "Odessa"),
    Poltava("Полтава", "Poltava"),
    Rivne("Рівне", "Rivne"),
    Sumy("Суми", "Sumy"),
    Ternopil("Тернопіль", "Ternopil"),
    Uzhhorod("Ужгород", "Uzhhorod"),
    Kharkiv("Харків", "Kharkiv"),
    Kherson("Херсон", "Kherson"),
    Khmelnytskyy("Хмельницький", "Khmelnytskyy"),
    Cherkasy("Черкаси", "Cherkasy"),
    Chernivtsi("Чернівці", "Chernivtsi"),
    Chernihiv("Чернігів", "Chernihiv");

    private String cityName;
    private String value;

    CityEnum(String name, String value) {
        this.cityName = name;
        this.value = value;
    }

    public String getCityName() {
        return cityName;
    }

    public String getValue() {
        return value;
    }

    public static List<CityEnum> cities() {
        List<CityEnum> cities = new ArrayList<>();
        cities.add(Vinnytsya);
        cities.add(Dniprodzerzhynsk);
        cities.add(Donetsk);
        cities.add(Zhytomyr);
        cities.add(Zaporizhzhya);
        cities.add(Ivano_Frankivsk);
        cities.add(Kirovohrad);
        cities.add(Luhansk);
        cities.add(Lutsk);
        cities.add(Lviv);
        cities.add(Mykolayiv);
        cities.add(Odessa);
        cities.add(Poltava);
        cities.add(Rivne);
        cities.add(Sumy);
        cities.add(Ternopil);
        cities.add(Uzhhorod);
        cities.add(Kharkiv);
        cities.add(Kherson);
        cities.add(Khmelnytskyy);
        cities.add(Cherkasy);
        cities.add(Chernivtsi);
        cities.add(Chernihiv);
        return cities;
    }

    public static CityEnum getByValue(String value) {
        Optional<CityEnum> cityEnum = Arrays.stream(values())
                .filter(t -> t.getValue().equals(value))
                .findFirst();
        if (cityEnum.isPresent()) {
            return cityEnum.get();
        } else {
            return null;
        }
    }

    public static CityEnum getByName(String name) {
        Optional<CityEnum> cityEnum = Arrays.stream(values())
                .filter(t -> t.getCityName().equals(name))
                .findFirst();
        if (cityEnum.isPresent()) {
            return cityEnum.get();
        } else {
            return null;
        }
    }
}

package com.svf.edu.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by stepanferubko
 */
@Repository
public class AuthoritiesRepository extends AbstractPropertiesRepository {
    private static final String AUTHORITIES_PROPERTIES = "authorities.properties";
    private String root = getClass().getClassLoader().getResource(AUTHORITIES_PROPERTIES).getPath();
    ;

//    @Value("${credentials.path}")
//    public void setRoot(String root) {
//        this.root=getClass().getClassLoader().getResource(AUTHORITIES_PROPERTIES).getPath();
////        this.root = root;
//    }
//
//    public Path getRoot() {
//        return Paths.get(root);
//    }

    public Map<String, String[]> findAll() {
        Properties properties = load(root, AUTHORITIES_PROPERTIES);
        return properties.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        o -> o.getKey().toString(),
                        o -> parseAuthorities(o.getValue()))
                );
    }

    public String[] remove(String login) {
        Properties removed = remove(root, login, AUTHORITIES_PROPERTIES);
        return parseAuthorities(removed.getProperty(login, ""));
    }

    public boolean save(String login, String[] authorities) {
        Map<String, String[]> data = new HashMap<>();
        data.putAll(findAll());
        if (Arrays.equals(data.get(login), authorities)) {
            return false;
        }
        data.put(login, authorities);
        saveAll(data);
        return true;
    }

    private String[] parseAuthorities(Object value) {
        if (value == null)
            return null;
        return Arrays.stream(value.toString().split(","))
                .map(StringUtils::trimToNull)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }

    private void saveAll(Map<String, String[]> data) {
        Properties properties = new Properties();
        data.forEach((login, authorities) -> {
            String authoritiesString = Arrays.stream(authorities).collect(Collectors.joining(","));
            properties.put(login, authoritiesString);
        });
        write(properties, root, AUTHORITIES_PROPERTIES);
    }
}

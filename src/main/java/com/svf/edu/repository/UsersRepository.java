package com.svf.edu.repository;

import com.svf.edu.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by stepanferubko
 */
@Repository
public class UsersRepository extends AbstractPropertiesRepository {
    //        private static final Logger LOG = AppLogger.getLogger(UsersRepository.class);
    private static final String USERS_PROPERTIES = "users.properties";
    private final String root = getClass().getClassLoader().getResource(USERS_PROPERTIES).getPath();

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

//    @Value("${credentials.path}")
//    public void setRoot(String root) {
//        this.root=getClass().getClassLoader().getResource(USERS_PROPERTIES).getPath();
//
//    }
//
//
//    public Path getRoot() {
//        return Paths.get(root);
//    }

    public User getByName(String login) {
        Properties users = load(root, USERS_PROPERTIES);
        Map<String, String[]> authorities = authoritiesRepository.findAll();
        Optional<String> first = users.stringPropertyNames().stream()
                .filter(u -> u.equals(login)).findFirst();
        User user = first.map(s -> User.newUser(login, users.getProperty(login), authorities.get(login))).orElse(null);
        return user;
    }

    public List<User> findAll() {
        Properties users = load(root, USERS_PROPERTIES);
        Map<String, String[]> authorities = authoritiesRepository.findAll();
        return users.stringPropertyNames().stream()
                .map(login -> User.newUser(login, users.getProperty(login), authorities.get(login)))
                .collect(Collectors.toList());
    }

    public User remove(String login) {
        String[] authorities = authoritiesRepository.remove(login);
        Properties removed = remove(root, login, USERS_PROPERTIES);
        if (removed.isEmpty())
            return null;
        return User.newUser(login, removed.getProperty(login), authorities);
    }

    public User save(User user) {
        String login = user.getLogin();
        authoritiesRepository.save(login, user.getAuthorities());
        Set<User> data = new HashSet<>();
        data.addAll(findAll());
        if (data.contains(user)) {
        } else {
            data.stream().filter(u -> u.getLogin().equals(login)).findFirst().ifPresent(u -> {
            });
            boolean removed = data.removeIf(u -> u.getLogin().equals(login));
            data.add(user);
            saveAll(data);
        }
        return user;
    }

    private void saveAll(Collection<User> data) {
        Properties properties = new Properties();
        properties.putAll(data.stream().collect(Collectors.toMap(User::getLogin, User::getPassword)));
        write(properties, root, USERS_PROPERTIES);
    }
}

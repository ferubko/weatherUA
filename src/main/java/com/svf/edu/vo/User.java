package com.svf.edu.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by stepanferubko
 */
public class User implements Serializable {
    private String login;
    private String password;
    private String[] authorities;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Arrays.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(login, password);
        return 31 * hash + Arrays.hashCode(authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + Arrays.toString(authorities) +
                '}';
    }


    public static User newUser(String login, String pass, String[] authorities) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        user.setAuthorities(authorities);
        return user;
    }
}

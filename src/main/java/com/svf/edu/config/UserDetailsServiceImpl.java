package com.svf.edu.config;

import com.svf.edu.repository.UsersRepository;
import com.svf.edu.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by stepanferubko
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static Logger LOG = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username);
        LOG.info("Trying to login with " + user.getLogin() + " " + user.getPassword());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String role : user.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}

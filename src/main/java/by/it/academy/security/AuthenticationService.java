package by.it.academy.security;

import by.it.academy.pojo.User;
import by.it.academy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("authService")
public class AuthenticationService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("Calling loadUserByUsername: {}", username);

        User user = userService.findByUserName(username);

        if (user == null) throw new UsernameNotFoundException("User not found: " + username);

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(appRole -> new SimpleGrantedAuthority("ROLE_" + appRole.getRoleName()))
                        .collect(Collectors.toList())
        );

    }
}

package com.mavericksstube.maverickshub.security.services;

import com.mavericksstube.maverickshub.models.User;
import com.mavericksstube.maverickshub.security.models.SecureUser;
import com.mavericksstube.maverickshub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByUsername(username);
            return new SecureUser(user);
        }
        catch (UsernameNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }
}

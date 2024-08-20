package com.mindhub.AppSprint2.configurationsjwt;

import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado con email: " + username));

        return new User(userEntity.getEmail(), userEntity.getPassword(), AuthorityUtils.createAuthorityList(
                userEntity.getRol().toString()
        ));
    }
}

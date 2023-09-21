package com.pragma.userservice.infraestructure.security.userdetails;

import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import com.pragma.userservice.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
        return new UserDetailsImpl(userEntity);
    }
}

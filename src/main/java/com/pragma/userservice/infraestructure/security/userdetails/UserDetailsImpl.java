package com.pragma.userservice.infraestructure.security.userdetails;

import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private UserEntity userEntity;

    public UserDetailsImpl(UserEntity userEntity){
        this.userEntity = userEntity;
    }
    public Long getId(){
        return userEntity.getId();
    }
    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.pragma.userservice.infraestructure.out.securitycontext;

import com.pragma.userservice.domain.spi.ISecurityContextPort;
import com.pragma.userservice.infraestructure.security.userdetails.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextAdapter implements ISecurityContextPort {
    @Override
    public String getRolFromSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
    }

    @Override
    public Long getIdFromSecurityContext() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId();
    }
}

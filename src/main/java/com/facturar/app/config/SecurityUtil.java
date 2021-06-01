package com.facturar.app.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public UserDetails getCurrentUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getCurrentUserId(){
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return Long.parseLong(id);
    }
}

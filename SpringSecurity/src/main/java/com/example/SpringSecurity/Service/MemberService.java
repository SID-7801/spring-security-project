package com.example.SpringSecurity.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService {
    UserDetailsService userDetailsService();
}

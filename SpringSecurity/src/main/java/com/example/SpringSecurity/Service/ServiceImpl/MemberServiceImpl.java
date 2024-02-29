package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Repository.MemberRepository;
import com.example.SpringSecurity.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {
                return memberRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user mot found"));
            }
        };
    }
}

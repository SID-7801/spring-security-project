package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.JwtAuthenticationResponse;
import com.example.SpringSecurity.Dao.Request.Signin;
import com.example.SpringSecurity.Dao.Request.Signup;
import com.example.SpringSecurity.Entity.Member;
import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Repository.MemberRepository;
import com.example.SpringSecurity.Service.AuthenticationService;
import com.example.SpringSecurity.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @Override
    public JwtAuthenticationResponse signup(Signup request) {
        var member = Member.builder().name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .wing(request.getWing())
                .flat(request.getFlat())
                .mobile(request.getMobile())
                .role(Role.USER).build();
        memberRepository.save(member);
        var jwt = jwtService.generateToken(member);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(Signin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));

        var member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find'"));

        var jwt = jwtService.generateToken(member);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.JwtAuthenticationResponse;
import com.example.SpringSecurity.Dao.Request.Signin;
import com.example.SpringSecurity.Dao.Request.Signup;
import com.example.SpringSecurity.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody Signup request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody Signin request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}

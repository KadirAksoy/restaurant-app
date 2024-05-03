package com.kadiraksoy.restaurantapp.controller;

import com.kadiraksoy.restaurantapp.payload.request.SignInRequest;
import com.kadiraksoy.restaurantapp.payload.request.SignUpRequest;
import com.kadiraksoy.restaurantapp.payload.response.JwtAuthenticationResponse;
import com.kadiraksoy.restaurantapp.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody SignUpRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.login(request);
    }
}
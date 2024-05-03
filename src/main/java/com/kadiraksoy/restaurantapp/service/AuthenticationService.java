package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.model.Role;
import com.kadiraksoy.restaurantapp.model.User;
import com.kadiraksoy.restaurantapp.payload.request.SignUpRequest;
import com.kadiraksoy.restaurantapp.payload.response.JwtAuthenticationResponse;
import com.kadiraksoy.restaurantapp.repository.UserRepository;
import com.kadiraksoy.restaurantapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                // Kayıt olan birinin rolünü User olarak yapıyoruz.
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);

        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

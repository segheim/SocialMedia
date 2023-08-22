package com.company.socialmedia.controller;

import com.company.socialmedia.entity.AppError;
import com.company.socialmedia.entity.AuthenticationRequest;
import com.company.socialmedia.entity.AuthenticationResponse;
import com.company.socialmedia.entity.UserDetailsImpl;
import com.company.socialmedia.service.impl.UserServiceImpl;
import com.company.socialmedia.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetailsImpl userDetails = userService.loadUserByUsername(authRequest.getName());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(userDetails.getUsername(), token), HttpStatus.OK);
    }
}

package com.company.socialmedia.controller;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.AuthenticationRequest;
import com.company.socialmedia.entity.AuthenticationResponse;
import com.company.socialmedia.entity.UserDetailsImpl;
import com.company.socialmedia.service.impl.UserServiceImpl;
import com.company.socialmedia.util.JwtTokenUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication Controller")
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> createAuthToken(@RequestBody AuthenticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        UserDetailsImpl userDetails = userService.loadUserByUsername(authRequest.getName());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(userDetails.getUsername(), token), HttpStatus.OK);
    }

    @PostMapping("/singup")
    public Long registration(UserDto userDto) {
        String encode = passwordEncoder.encode(userDto.getPassword());
        return 1L;
    }
}

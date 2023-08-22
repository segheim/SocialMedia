package com.company.socialmedia.entity;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String name;
    private String password;
}

package com.company.socialmedia.dto;

import lombok.*;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}

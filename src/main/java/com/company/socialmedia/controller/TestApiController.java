package com.company.socialmedia.controller;

import com.company.socialmedia.entity.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApiController {

    @GetMapping("/v2")
    public String getS(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();


        System.out.println(name);
        return "Hello";
    }
}

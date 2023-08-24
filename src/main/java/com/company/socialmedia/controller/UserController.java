package com.company.socialmedia.controller;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.exception.AlreadyExistException;
import com.company.socialmedia.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.readById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<List<UserDto>> readAll(Pageable pageable) {
        return new ResponseEntity<>(userService.readAll(pageable), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/friendship/{id}")
    public ResponseEntity<Long> addFriend(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.addFriend(id), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/friendship_request/{id}")
    public ResponseEntity<Long> addFriendshipRequest(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.addFriendshipRequest(id), HttpStatus.CREATED);
    }
}

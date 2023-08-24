package com.company.socialmedia.controller;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "User Controller", description = "Interaction with users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.readById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows get all users id")
    @GetMapping
    public ResponseEntity<List<UserDto>> readAll(Pageable pageable) {
        return new ResponseEntity<>(userService.readAll(pageable), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows create new user")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows update user by id")
    @PostMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.CREATED);
    }
}

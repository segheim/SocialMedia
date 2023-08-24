package com.company.socialmedia.controller;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.service.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{owner_id}")
    public ResponseEntity<List<PostDto>> readById(@PathVariable(name = "owner_id") Long id) {
        return new ResponseEntity<>(postService.readByUserId(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.create(postDto), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody PostDto postDto, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> remove(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }
}

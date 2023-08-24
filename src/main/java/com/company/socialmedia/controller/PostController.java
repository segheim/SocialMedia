package com.company.socialmedia.controller;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.service.impl.PostServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "Post Controller", description = "Interaction with user's posts")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows get post by id")
    @GetMapping("/{owner_id}")
    public ResponseEntity<List<PostDto>> readById(@PathVariable(name = "owner_id") Long id) {
        try {
            return new ResponseEntity<>(postService.readByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows create new post")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.create(postDto), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows update post by id")
    @PostMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody PostDto postDto, @PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @ApiOperation(value = "Allows remove post by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> remove(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }
}

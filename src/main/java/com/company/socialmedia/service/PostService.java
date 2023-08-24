package com.company.socialmedia.service;

import com.company.socialmedia.dto.PostDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface PostService {

    List<PostDto> readByUserId(Long id);
    long create(@Valid PostDto postDto);
    long update(@Valid PostDto postDto, Long id);
    long delete(Long id);
}

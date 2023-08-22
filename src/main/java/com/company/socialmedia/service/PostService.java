package com.company.socialmedia.service;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDto> readByUserId(Long id);

    long create(PostDto postDto);
    long update(Long id);
    long delete(Long id);
}

package com.company.socialmedia.mapper;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.entity.Post;

public interface PostMapper extends AbstractMapper<Post, PostDto>{

    void updatePostFromDto(PostDto postDto, Post post);
}

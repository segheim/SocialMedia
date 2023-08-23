package com.company.socialmedia.mapper.impl;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.Post;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.mapper.PostMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post object) {
        if (object == null) {
            return null;
        }
        return PostDto.builder()
                .id(object.getId())
                .userId(object.getId())
                .title(object.getTitle())
                .text(object.getText())
                .picture(object.getPicture())
                .build();
    }

    @Override
    public Post fromDto(PostDto postDto) {
        if (postDto == null) {
            return  null;
        }
        User user = new User();
        user.setId(postDto.getUserId());
        return Post.builder()
                .user(user)
                .title(postDto.getTitle())
                .text(postDto.getText())
                .picture(postDto.getPicture())
                .build();
    }

    @Override
    public List<PostDto> toDtoList(List<Post> objects) {
        if (objects == null) {
            return null;
        }
        List<PostDto> list = new ArrayList<>(objects.size());
        for (Post post : objects) {
            list.add(this.toDto(post));
        }
        return list;
    }

    @Override
    public List<Post> fromDtoList(List<PostDto> postDtos) {
        if (postDtos == null) {
            return null;
        }
        List<Post> list = new ArrayList<>(postDtos.size());
        for (PostDto postDto : postDtos) {
            list.add(this.fromDto(postDto));
        }
        return list;
    }

    @Override
    public void updatePostFromDto(PostDto postDto, Post post) {
        if (postDto.getTitle() != null) {
            post.setTitle(postDto.getTitle());
        } if (postDto.getText() != null) {
            post.setText(postDto.getText());
        } if (postDto.getPicture() != null) {
            post.setPicture(postDto.getPicture());
        }
    }
}

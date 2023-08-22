package com.company.socialmedia.service.impl;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.entity.Post;
import com.company.socialmedia.mapper.PostMapper;
import com.company.socialmedia.repository.PostRepository;
import com.company.socialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> readByUserId(Long id) {
        List<Post> posts = postRepository.findPostsByUser_Id(id);
        return postMapper.toDtoList(posts);
    }

    @Override
    public long create(PostDto postDto) {
        Post savePost = postRepository.save(postMapper.fromDto(postDto));
        return savePost.getId();
    }

    @Override
    public long update(Long id) {
        return 0;
    }

    @Override
    public long delete(Long id) {
        return 0;
    }
}

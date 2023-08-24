package com.company.socialmedia.service.impl;

import com.company.socialmedia.dto.PostDto;
import com.company.socialmedia.entity.Post;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.entity.UserDetailsImpl;
import com.company.socialmedia.exception.NotFoundExceptionService;
import com.company.socialmedia.mapper.PostMapper;
import com.company.socialmedia.repository.PostRepository;
import com.company.socialmedia.repository.UserRepository;
import com.company.socialmedia.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> readByUserId(Long id) {
        List<Post> posts = postRepository.findPostsByUser_Id(id);
        return postMapper.toDtoList(posts);
    }

    @Override
    public long create(PostDto postDto) {
        Post post = postMapper.fromDto(postDto);
        User user = userRepository.findById(post.getUser().getId()).orElseThrow(() -> new NotFoundExceptionService("User not found"));
        post.setUser(user);
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }

    @Override
    public long update(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("Post not found"));
        postMapper.updatePostFromDto(postDto, post);
        return postRepository.save(post).getId();
    }

    @Override
    public long delete(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Optional<User> user = userRepository.findUserByName(userDetails.getUsername());
        if(!user.isPresent() && user.get().getId() != id) {
            log.error("Absent permissions for delete");
            throw new NotFoundExceptionService("Absent permissions");
        }
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("Post not found"));
        postRepository.delete(post);
        return post.getId();
    }
}

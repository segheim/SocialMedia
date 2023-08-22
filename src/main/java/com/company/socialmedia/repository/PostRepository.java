package com.company.socialmedia.repository;

import com.company.socialmedia.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findPostsByUser_Id(Long id);

}

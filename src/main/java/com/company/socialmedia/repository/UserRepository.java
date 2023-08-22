package com.company.socialmedia.repository;

import com.company.socialmedia.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByName(String name);

    Boolean existsUserByEmail(String email);

}

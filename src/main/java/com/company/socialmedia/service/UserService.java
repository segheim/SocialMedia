package com.company.socialmedia.service;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.exception.NotFoundExceptionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    UserDto readById(Long id) throws NotFoundExceptionService;
    User readByEmail(String email);
    List<UserDto> readAll(Pageable pageable);
    long create(@Valid UserDto userDto);
    long update(@Valid UserDto userDto, Long id);
}

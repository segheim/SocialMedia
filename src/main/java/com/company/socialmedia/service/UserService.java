package com.company.socialmedia.service;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    UserDto readById(Long id) throws ServiceException;
    User readByEmail(String email);
    List<UserDto> readAll(Pageable pageable);
    long create(UserDto userDto);
    long update(UserDto userDto, Long id);
}

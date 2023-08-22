package com.company.socialmedia.mapper.impl;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User object) {
        if (object == null) {
            return null;
        }
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .build();
    }

    @Override
    public User fromDto(UserDto userDto) {
        if (userDto == null) {
            return  null;
        }
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> objects) {
        if (objects == null) {
            return null;
        }
        List<UserDto> list = new ArrayList<>(objects.size());
        for (User user : objects) {
            list.add(this.toDto(user));
        }
        return list;
    }

    @Override
    public List<User> fromDtoList(List<UserDto> userDtos) {
        if (userDtos == null) {
            return null;
        }
        List<User> list = new ArrayList<>(userDtos.size());
        for (UserDto userDto : userDtos) {
            list.add(this.fromDto(userDto));
        }
        return list;
    }

    @Override
    public void updateUserFromDto(UserDto userDto, User user) {
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        } if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        } if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
    }
}

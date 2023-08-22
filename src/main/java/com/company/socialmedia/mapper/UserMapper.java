package com.company.socialmedia.mapper;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper extends AbstractMapper<User, UserDto> {
    void updateUserFromDto(UserDto userDto, User user);
}

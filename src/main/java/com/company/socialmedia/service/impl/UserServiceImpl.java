package com.company.socialmedia.service.impl;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.EnumRole;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.entity.UserDetailsImpl;
import com.company.socialmedia.exception.NotFoundExceptionService;
import com.company.socialmedia.mapper.UserMapper;
import com.company.socialmedia.repository.RoleRepository;
import com.company.socialmedia.repository.UserRepository;
import com.company.socialmedia.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Service
@Validated
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto readById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("User not found")));
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundExceptionService("User not found"));
    }

    @Override
    public List<UserDto> readAll(Pageable pageable) {
        return userMapper.toDtoList(userRepository.findAll(pageable).getContent());
    }

    public long create(@Valid UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        user.setRoles(Set.of(roleRepository.findByName(EnumRole.USER).orElseThrow(() ->new NotFoundExceptionService("Role not found"))));
        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    @Override
    public long update(@Valid UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("User not found"));
        userMapper.updateUserFromDto(userDto, user);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return UserDetailsImpl.build(user);
    }
}

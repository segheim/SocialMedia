package com.company.socialmedia.service.impl;

import com.company.socialmedia.dto.UserDto;
import com.company.socialmedia.entity.EnumRole;
import com.company.socialmedia.entity.User;
import com.company.socialmedia.entity.UserDetailsImpl;
import com.company.socialmedia.exception.AlreadyExistException;
import com.company.socialmedia.exception.NotFoundExceptionService;
import com.company.socialmedia.mapper.UserMapper;
import com.company.socialmedia.repository.RoleRepository;
import com.company.socialmedia.repository.UserRepository;
import com.company.socialmedia.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

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

    public long create(UserDto userDto){
        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new AlreadyExistException("User already exist");
        }
        User user = userMapper.fromDto(userDto);
        user.setRoles(Set.of(roleRepository.findByName(EnumRole.USER).orElseThrow(() ->new NotFoundExceptionService("Role not found"))));
        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    @Override
    public long update(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("User not found"));
        userMapper.updateUserFromDto(userDto, user);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public long addFriend(Long id) {
        User userFriend = userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("Not found user"));
        User user = getCurrentUser();
        User user1 = userRepository.findById(user.getId()).get();
        List<User> friends = user1.getFriends();
        if (friends.contains(userFriend)) {
            throw new AlreadyExistException(String.format("User already have friend with id=%d", id));
        }
        friends.add(userFriend);
        User saveUser = userRepository.save(user1);
        Long userId = saveUser.getId();
        return userId;
    }

    @Override
    @Transactional
    public long addFriendshipRequest(Long id) {
        User userToFriendship = userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionService("Not found user"));
        User user = getCurrentUser();

        user.getFriendshipRequest().add(userToFriendship);
        return userRepository.save(user).getId();
    }

    @Transactional
    public Integer getFriends(Long id) {
        User user = userRepository.findById(id).get();
        List<User> friends = user.getFriends();
        return friends.size();
    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return UserDetailsImpl.build(user);
    }

    private User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null) {
            throw new UsernameNotFoundException("Can't get user name from auth request");
        }
        User user = userRepository.findUserByName(name).orElseThrow(() -> new NotFoundExceptionService("Not found user"));
        return user;
    }


}

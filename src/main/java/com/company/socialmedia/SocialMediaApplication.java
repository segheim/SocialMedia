package com.company.socialmedia;

import com.company.socialmedia.entity.User;
import com.company.socialmedia.repository.UserRepository;
import com.company.socialmedia.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SocialMediaApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {

//            userService.addFriend(1L,2L);
            Integer friends = userService.getFriends(3L);

            System.out.println(friends);


//            User user2 = userRepository.findById(2L).get();
//            User user3 = userRepository.findById(3L).get();
//
//            user3.getFriends().add(user2);
//
//            userRepository.save(user3);
        };

    }
}

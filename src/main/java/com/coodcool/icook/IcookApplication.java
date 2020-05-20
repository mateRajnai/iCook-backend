package com.coodcool.icook;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.Role;
import com.coodcool.icook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class IcookApplication {

    @Autowired
    UserRepository userRepository;

    List<Role> roles = new ArrayList<> ();

    public static void main(String[] args) {
        SpringApplication.run(IcookApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            roles.add(Role.ADMIN);
            roles.add(Role.USER);

            User user = User.builder()
                    .userName("user1")
                    .password(passwordEncoder.encode("password"))
                    .roles(roles)
                    .build();

            userRepository.save(user);
        };


    }
}

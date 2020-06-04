package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.dto.UserCredentials;
import com.coodcool.icook.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RegistrationHandler {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final LoginHandler loginHandler;
    public Map<Object, Object> handleRegistration(User user) {
        UserCredentials userCredentials = new UserCredentials(user.getUserName(), user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
        return this.loginHandler.handleLogin(userCredentials);
    }

    public Cookie getGeneratedCookie(Map<Object, Object> userData) {
        return this.loginHandler.generateCookie(userData);
    }

    public boolean checkIfUsernameExists(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.isPresent();
    }

    public boolean checkIfEmailRegistered(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}

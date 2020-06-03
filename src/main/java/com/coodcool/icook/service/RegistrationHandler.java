package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.User;
import com.coodcool.icook.model.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistrationHandler {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private LoginHandler loginHandler;

    public  void handleRegistration(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        this.doLogin(user.getUserName(), user.getPassword());

    }

    private void doLogin(String username, String password) {
        loginHandler.handleLogin(new UserCredentials(username, password));
    }

}

package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.User;
import com.coodcool.icook.model.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RegistrationHandler {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final UserRepository userRepository;

    private final LoginHandler loginHandler;
    public Map<Object, Object> handleRegistration(User user) {
        UserCredentials userCredentials = new UserCredentials(user.getUserName(), user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
        return this.loginHandler.handleLogin(userCredentials);
    }

    public Cookie getGeneratedCookie() {
        return this.loginHandler.getGeneratedCookie();
    }

}

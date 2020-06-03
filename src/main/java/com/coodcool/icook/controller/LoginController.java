package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.User;
import com.coodcool.icook.dto.UserCredentials;
import com.coodcool.icook.security.JwtTokenServices;
import com.coodcool.icook.service.LoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginHandler loginHandler;


    @PostMapping
    public ResponseEntity<Map<Object, Object>> login(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            Map<Object, Object> userData = loginHandler.handleLogin(data);
            response.addCookie(loginHandler.generateCookie(userData));

            return ResponseEntity.ok(userData);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}

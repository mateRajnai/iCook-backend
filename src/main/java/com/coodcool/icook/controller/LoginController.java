package com.coodcool.icook.controller;

import com.coodcool.icook.dto.UserCredentials;
import com.coodcool.icook.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping
    public ResponseEntity<Map<Object, Object>> login(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            Map<Object, Object> userData = loginService.handleLogin(data);
            response.addCookie(loginService.generateCookie(userData));

            return ResponseEntity.ok(userData);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}

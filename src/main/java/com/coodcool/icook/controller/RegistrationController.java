package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.User;
import lombok.RequiredArgsConstructor;
import com.coodcool.icook.service.RegistrationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationHandler registrationHandler;

    @PostMapping("")
    public ResponseEntity<Map<Object, Object>> register(@RequestBody User user, HttpServletResponse response) {
        try {
            Map<Object, Object> userData = registrationHandler.handleRegistration(user);
            response.addCookie(registrationHandler.getGeneratedCookie(userData));
            return ResponseEntity.ok(userData);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping("/check_username")
    public ResponseEntity<Boolean> checkUsername(@RequestBody String username) {
        return ResponseEntity.ok(registrationHandler.checkIfUsernameExists(username));
    }

    @PostMapping("/check_email")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
        return ResponseEntity.ok(registrationHandler.checkIfEmailRegistered(email));
    }
}

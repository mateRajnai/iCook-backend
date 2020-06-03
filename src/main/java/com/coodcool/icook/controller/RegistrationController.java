package com.coodcool.icook.controller;

import com.coodcool.icook.model.User;
import com.coodcool.icook.service.RegistrationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {

    private RegistrationHandler registrationHandler;

    @PostMapping("")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            registrationHandler.handleRegistration(user);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}

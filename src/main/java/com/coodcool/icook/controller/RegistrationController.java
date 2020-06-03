package com.coodcool.icook.controller;

import com.coodcool.icook.model.User;
import com.coodcool.icook.service.RegistrationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationHandler registrationHandler;

    @PostMapping("")
    public ResponseEntity register(@RequestBody User user, HttpServletResponse response) {
        try {
            Map<Object, Object> userData = registrationHandler.handleRegistration(user);
            response.addCookie(registrationHandler.getGeneratedCookie(userData));
            return ResponseEntity.ok(userData);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}

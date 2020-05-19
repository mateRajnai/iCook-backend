package com.coodcool.icook.controller;

import com.coodcool.icook.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/signup")
public class RegistrationController {

    @PostMapping("")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        return null;
    }
}

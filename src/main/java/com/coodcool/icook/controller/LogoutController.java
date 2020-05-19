package com.coodcool.icook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
@CrossOrigin(origins = "http://localhost:3000")
public class LogoutController {

    @GetMapping("")
    public ResponseEntity getPersonalNotes(@RequestBody String jwtToken) {
        return ResponseEntity.ok("");
    }

}

package com.coodcool.icook.controller;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
@CrossOrigin(origins = "http://localhost:3000")
public class LogoutController {

    private JwtTokenBlackListDaoMem blackListDaoMem;

    public LogoutController(JwtTokenBlackListDaoMem blackListDaoMem){
        this.blackListDaoMem = blackListDaoMem;
    }

    @GetMapping("")
    public ResponseEntity getPersonalNotes(@RequestHeader("Authorization") String token) {

            if (token != null && token.startsWith("Bearer ")) {
                String onlyJwtTokenFromString = token.substring(7);
                blackListDaoMem.getJwtTokenBlackList().add(onlyJwtTokenFromString);
                return ResponseEntity.ok("");
            }
            return ResponseEntity.badRequest().body("Header is not correct.");
    }

}

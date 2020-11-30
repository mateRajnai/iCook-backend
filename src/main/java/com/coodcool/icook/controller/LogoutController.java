package com.coodcool.icook.controller;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    private JwtTokenBlackListDaoMem blackListDaoMem;

    public LogoutController(JwtTokenBlackListDaoMem blackListDaoMem){
        this.blackListDaoMem = blackListDaoMem;
    }

    @PostMapping("")
    public ResponseEntity getPersonalNotes(@RequestHeader("Authorization") String token) {

            if (token != null && token.startsWith("Bearer ")) {
                String onlyJwtTokenFromString = token.substring(7);
                blackListDaoMem.getJwtTokenBlackList().add(onlyJwtTokenFromString);
                System.out.println(blackListDaoMem.toString());
                return ResponseEntity.ok("");
            }
            return ResponseEntity.badRequest().body("Header is not correct.");
    }

}

package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtDoBlackListing implements LogoutHandler {

    private JwtTokenBlackListDaoMem blackList;

    private JwtTokenServices jwtTokenServices;

    JwtDoBlackListing(JwtTokenServices jwtTokenServices, JwtTokenBlackListDaoMem blacklist) {
        this.jwtTokenServices = jwtTokenServices;
        this.blackList = blacklist;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = jwtTokenServices.getTokenFromRequest(request);
        if (token != null) {
            blackList.addTokenToBlackList(token);
        }
    }
}

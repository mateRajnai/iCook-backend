package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

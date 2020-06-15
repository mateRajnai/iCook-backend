package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtDoBlackListing implements LogoutHandler {

    private final JwtTokenBlackListDaoMem blackList;
    private final JwtTokenServices jwtTokenServices;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = jwtTokenServices.getTokenFromRequest(request);
        if (token != null) {
            blackList.addTokenToBlackList(token);
        }
        response.addCookie(jwtTokenServices.invalidateJwtCookie(request));
    }
}

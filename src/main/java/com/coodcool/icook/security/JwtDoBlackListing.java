package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtDoBlackListing extends GenericFilterBean {

    @Autowired
    private JwtTokenBlackListDaoMem blackList;

    private JwtTokenServices jwtTokenServices;

    JwtDoBlackListing(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenServices.getTokenFromRequest((HttpServletRequest) servletRequest);
        System.out.println("JwtDoBlackListing class ran.");
        if (token != null && token.startsWith("Bearer ")) {
            String onlyJwtTokenFromString = token.substring(7);
            blackList.getJwtTokenBlackList().add(onlyJwtTokenFromString);

            if (logger.isDebugEnabled()) {
                logger.debug("This token added to blacklist: " + blackList.toString());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

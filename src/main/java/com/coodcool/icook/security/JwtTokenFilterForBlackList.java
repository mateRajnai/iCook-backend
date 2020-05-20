package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilterForBlackList extends GenericFilterBean {

    @Autowired
    private JwtTokenBlackListDaoMem blackList;

    private JwtTokenServices jwtTokenServices;

    JwtTokenFilterForBlackList(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenServices.getTokenFromRequest((HttpServletRequest) servletRequest);
        //if the token is blacklisted then the filter chain will end
        if(token != null && !blackList.getJwtTokenBlackList().contains(token)) {
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

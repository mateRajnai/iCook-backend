package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilterForBlackList extends GenericFilterBean {

    private JwtTokenBlackListDaoMem blackList;
    private JwtTokenServices jwtTokenServices;

    JwtTokenFilterForBlackList(JwtTokenBlackListDaoMem blackList, JwtTokenServices jwtTokenServices) {
        this.blackList = blackList;
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenServices.getTokenFromRequest((HttpServletRequest) servletRequest);
        //if the token is blacklisted then the filter chain will end
        if(!blackList.getJwtTokenBlackList().contains(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}

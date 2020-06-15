package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilterForBlackList extends GenericFilterBean {

    private final JwtTokenBlackListDaoMem blackList;
    private final JwtTokenServices jwtTokenServices;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenServices.getTokenFromRequest((HttpServletRequest) servletRequest);
        //if the token is blacklisted then the filter chain will end
        if(token != null && blackList.getJwtTokenBlackList().contains(token)) {
            System.out.println("This token on the blacklist.");
            throw new AuthorizationServiceException("Invalid token (on blacklist)");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

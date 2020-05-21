package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
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
import java.io.IOException;

public class JwtTokenFilterForBlackList extends GenericFilterBean {

    private JwtTokenBlackListDaoMem blackList;

    private JwtTokenServices jwtTokenServices;

    JwtTokenFilterForBlackList(JwtTokenServices jwtTokenServices, JwtTokenBlackListDaoMem blackList) {
        this.jwtTokenServices = jwtTokenServices;
        this.blackList = blackList;
    }

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

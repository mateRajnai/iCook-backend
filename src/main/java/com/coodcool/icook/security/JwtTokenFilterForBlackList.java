package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class JwtTokenFilterForBlackList extends GenericFilterBean {

    private JwtTokenBlackListDaoMem blackList;

    JwtTokenFilterForBlackList(JwtTokenBlackListDaoMem blackList) {
        this.blackList = blackList;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}

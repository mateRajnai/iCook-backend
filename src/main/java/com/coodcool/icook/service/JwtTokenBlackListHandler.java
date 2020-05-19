package com.coodcool.icook.service;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenBlackListHandler {

    @Autowired
    JwtTokenBlackListDaoMem blackList;



}

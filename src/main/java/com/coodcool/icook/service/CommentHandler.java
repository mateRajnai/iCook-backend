package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentHandler {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;



}

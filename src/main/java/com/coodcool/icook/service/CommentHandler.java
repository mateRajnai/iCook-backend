package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CommentHandler {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment handleAddingComment(Comment comment) {
        comment.setSubmissionTime(LocalDateTime.now());
        this.commentRepository.save(comment);
        Long commentId = comment.getId();
        return this.commentRepository.findCommentById(commentId);

    }

}

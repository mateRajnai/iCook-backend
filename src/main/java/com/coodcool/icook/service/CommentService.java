package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.Comment;
import com.coodcool.icook.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<Comment> handleGettingComments(String id) {
        return this.commentRepository.findAllByRecipeIdOrderBySubmissionTimeDesc(id);

    }

    public Comment handleAddingComment(Comment comment, String userId) {
        comment.setSubmissionTime(LocalDateTime.now());
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        comment.setUser(user);
        this.commentRepository.save(comment);
        user.setComments(Set.of(comment));
        Long commentId = comment.getId();
        return this.commentRepository.findCommentById(commentId);

    }


}

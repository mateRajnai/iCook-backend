package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/recipe/{id}/comments")
public class CommentController {

    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> getCommentsBy(@PathVariable("id") String id) {
        return this.commentRepository.findAllByRecipeIdOrderBySubmissionTimeDesc(id);
    }

    @PostMapping("")
    public Comment addComment(@RequestBody Comment comment) {
        comment.setSubmissionTime(LocalDateTime.now());
        this.commentRepository.save(comment);
        Long commentId = comment.getId();
        return this.commentRepository.findCommentById(commentId);
    }

}

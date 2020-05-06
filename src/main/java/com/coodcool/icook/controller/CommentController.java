package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsBy(@PathVariable("id") String id) {
        return this.commentRepository.findAllByRecipeId(id);
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@RequestBody Comment comment, @PathVariable("id") String id) {
        comment.setSubmissionTime(LocalDateTime.now());
        this.commentRepository.save(comment);
        Long commentId = comment.getId();
        return this.commentRepository.findCommentById(commentId);
    }

}

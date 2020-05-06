package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/recipe/{id}/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> getCommentsBy(@PathVariable("id") String id) {
        return this.commentRepository.findAllByRecipeId(id);
    }

    @PostMapping("")
    public Comment addComment(@RequestBody Comment comment) {
        comment.setSubmissionTime(LocalDateTime.now());
        this.commentRepository.save(comment);
        Long commentId = comment.getId();
        return this.commentRepository.findCommentById(commentId);
    }

}

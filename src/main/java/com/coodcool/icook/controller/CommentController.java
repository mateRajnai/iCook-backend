package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.CommentRepository;
import com.coodcool.icook.model.Comment;
import com.coodcool.icook.service.CommentHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/recipe/{id}/comments")
public class CommentController {

    private CommentRepository commentRepository;
    private CommentHandler commentHandler;

    @GetMapping("")
    public List<Comment> getCommentsBy(@PathVariable("id") String id) {
        return this.commentRepository.findAllByRecipeIdOrderBySubmissionTimeDesc(id);
    }

    @PostMapping("")
    public Comment addComment(@RequestBody Comment comment) {
        return commentHandler.handleAddingComment(comment);
    }

}

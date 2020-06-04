package com.coodcool.icook.controller;

import com.coodcool.icook.model.Comment;
import com.coodcool.icook.service.CommentHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/recipe/{id}/comments")
public class CommentController {

    private CommentHandler commentHandler;

    @GetMapping("")
    public ResponseEntity<List<Comment>> getCommentsBy(@PathVariable("id") String id) {
        return ResponseEntity.ok(commentHandler.handleGettingComments(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(commentHandler.handleAddingComment(comment, userId));
    }

}

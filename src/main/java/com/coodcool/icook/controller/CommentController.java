package com.coodcool.icook.controller;

import com.coodcool.icook.dao.CommentDao;
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
    private CommentDao commentDao;

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsBy(@PathVariable("id") String id) {

        return this.commentDao.getCommentsBy(id);
    }

    @PostMapping("/{id}/comments")
    public List<Comment> addComment(@RequestBody Comment comment, @PathVariable("id") String id) {
        comment.setSubmissionTime(LocalDateTime.now());
        this.commentDao.addComment(comment);
        return this.commentDao.getCommentsBy(id);
    }

}

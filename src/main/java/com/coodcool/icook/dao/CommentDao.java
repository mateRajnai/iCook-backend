package com.coodcool.icook.dao;

import com.coodcool.icook.model.Comment;

import java.util.List;

public interface CommentDao {

    void addComment(Comment comment);

    List<Comment> getCommentsBy(String recipeId);

    void editComment(Comment comment);

    void deleteComment(String id);

}

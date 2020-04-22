package com.coodcool.icook.dao.implementation;

import com.coodcool.icook.dao.CommentDao;
import com.coodcool.icook.model.Comment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDaoMem implements CommentDao {

    private List<Comment> comments = new LinkedList<>();


    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getCommentsBy(String recipeId) {
        return comments
                .stream()
                .filter(comment -> comment
                        .getRecipeId()
                        .equals(recipeId))
                .collect(Collectors.toList());
    }


    @Override
    public void editComment(Comment comment) {
    }

    @Override
    public void deleteComment(String id) {

    }
}

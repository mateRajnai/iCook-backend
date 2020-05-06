package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRecipeIdOrderBySubmissionTimeDesc(String recipeId);
    Comment findCommentById(Long id);
}

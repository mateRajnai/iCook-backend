package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.Comment;
import com.coodcool.icook.mother.CommentMother;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static  org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveOneComment() {
        Comment comment = CommentMother.completeWithoutId().build();
        commentRepository.save(comment);
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).hasSize(1);
    }


    @Test
    public void submissionTimeShouldBeNotNull() {
        Comment comment = CommentMother.completeWithoutIdAndSubmissionTime()
                .build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            commentRepository.saveAndFlush(comment);
        });

    }

    @Test
    public void recipeIdShouldBeNotNull() {
        Comment comment = CommentMother.completeWithoutIdAndRecipeId().build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            commentRepository.saveAndFlush(comment);
        });
    }

    @Test
    public void findAllByRecipeIdOrderBySubmissionTimeDesc() {
        Comment comment1 = CommentMother
                .completeWithoutIdAndSubmissionTime()
                .submissionTime(LocalDateTime.now())
                .build();
        Comment comment2 = CommentMother
                .completeWithoutIdAndSubmissionTime()
                .submissionTime(LocalDateTime.now().plusMinutes(1))
                .build();
        Comment comment3 = CommentMother
                .completeWithoutIdAndRecipeId()
                .recipeId("recipe id 2")
                .build();
        commentRepository.saveAll(Lists.newArrayList(comment1, comment2, comment3));
        String recipeId = CommentMother.getRecipeId();
        List<Comment> comments = commentRepository.findAllByRecipeIdOrderBySubmissionTimeDesc(recipeId);
        assertThat(comments)
                .hasSize(2)
                .containsExactly(comment2, comment1);
    }

    @Test
    public void findCommentById() {
        Comment comment1 = CommentMother.completeWithoutId().build();
        Comment comment2 = CommentMother.completeWithoutId().build();
        commentRepository.saveAll(Lists.newArrayList(comment1, comment2));
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).hasSize(2);
        Comment foundComment = commentRepository.findCommentById(2L);
        assertEquals(comment1, foundComment);
    }

}
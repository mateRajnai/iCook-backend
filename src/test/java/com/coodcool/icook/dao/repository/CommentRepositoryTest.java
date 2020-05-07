package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.*;
import com.coodcool.icook.mother.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static  org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveOneCompleteComment() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        Comment comment = CommentMother.withoutIdAndWithCustomUser(user).build();
        user.setComments(Set.of(comment));
        userRepository.save(user);
//  Q:      commentRepository.saveAndFlush(comment);
        assertThat(commentRepository.findAll()).hasSize(1).containsExactly(comment);
        Optional<Comment> queriedComment = commentRepository.findById(comment.getId());
        assertThat(queriedComment).isNotEmpty();
        assertThat(queriedComment.get().getContent()).isNotNull();
        assertThat(queriedComment.get().getSubmissionTime()).isNotNull();
        assertThat(queriedComment.get().getRecipeId()).isNotNull();
        assertThat(queriedComment.get().getUser()).isNotNull();

    }


    @Test
    public void submissionTimeShouldBeNotNull() {
        Comment comment = CommentMother.completeWithoutUserAndIdAndSubmissionTime()
                .build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            commentRepository.saveAndFlush(comment);
        });

    }

    @Test
    public void recipeIdShouldBeNotNull() {
        Comment comment = CommentMother.completeWithoutUserAndIdAndRecipeId().build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            commentRepository.saveAndFlush(comment);
        });
    }

    @Test
    public void findAllByRecipeIdOrderBySubmissionTimeDesc() {
        Comment comment1 = CommentMother
                .completeWithoutUserAndIdAndSubmissionTime()
                .submissionTime(LocalDateTime.now())
                .build();
        Comment comment2 = CommentMother
                .completeWithoutUserAndIdAndSubmissionTime()
                .submissionTime(LocalDateTime.now().plusMinutes(1))
                .build();
        Comment comment3 = CommentMother
                .completeWithoutUserAndIdAndRecipeId()
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
        Comment comment1 = CommentMother.withoutUserAndId().build();
        Comment comment2 = CommentMother.withoutUserAndId().build();
        commentRepository.saveAll(Lists.newArrayList(comment1, comment2));
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).hasSize(2);
        Comment foundComment = commentRepository.findCommentById(comment1.getId());
        assertEquals(comment1, foundComment);

    }

}
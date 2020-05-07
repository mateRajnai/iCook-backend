package com.coodcool.icook.mother;

import com.coodcool.icook.model.Comment;
import com.coodcool.icook.model.User;

import java.time.LocalDateTime;
import java.time.Month;

public class CommentMother {

    private static final String CONTENT = "This is very delicious and healthy";
    private static final String RECIPE_ID = "Recipe id";
    private static final LocalDateTime submissionTime = LocalDateTime.of(2019, Month.AUGUST, 10, 10,10,10);

    public static Comment.CommentBuilder withoutUserAndId() {
        return Comment.builder()
                .content(CONTENT)
                .recipeId(RECIPE_ID)
                .submissionTime(submissionTime);
    }

    public static Comment.CommentBuilder withoutUserAndWithCustomId(Long id) {
        return withoutUserAndId()
                .id(id);
    }

    public static Comment.CommentBuilder withoutIdAndWithCustomUser(User user) {
        return withoutUserAndId()
                .user(user);
    }

    public static Comment.CommentBuilder completeWithoutUserAndIdAndSubmissionTime() {
        return Comment.builder()
                .content(CONTENT)
                .recipeId(RECIPE_ID);
    }

    public static Comment.CommentBuilder completeWithoutUserAndIdAndRecipeId() {
        return Comment.builder()
                .content(CONTENT)
                .submissionTime(submissionTime);
    }

    public static Comment.CommentBuilder completeWithCustomIdAndUser(Long id, User user) {
        return withoutUserAndWithCustomId(id)
                .user(user);
    }


    public static String getRecipeId() {
        return RECIPE_ID;
    }
}

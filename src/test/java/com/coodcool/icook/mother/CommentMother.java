package com.coodcool.icook.mother;

import com.coodcool.icook.model.Comment;

import java.time.LocalDateTime;

public class CommentMother {

    private static final String RECIPE_ID = "http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39";
    private static final String CONTENT = "Test comment";

    public static Comment.CommentBuilder completeWithoutId() {
        return Comment.builder()
                .content(CONTENT)
                .recipeId(RECIPE_ID)
                .submissionTime(LocalDateTime.now());
    }

    public static Comment.CommentBuilder completeWithoutIdAndSubmissionTime() {
        return Comment.builder()
                .content(CONTENT)
                .recipeId(RECIPE_ID);
    }

    public static Comment.CommentBuilder completeWithoutIdAndRecipeId() {
        return Comment.builder()
                .content(CONTENT)
                .submissionTime(LocalDateTime.now());
    }

    public static String getRecipeId() {
        return RECIPE_ID;
    }
}

package com.coodcool.icook.mother;

import com.coodcool.icook.model.Comment;

import java.time.LocalDateTime;

public class CommentMother {

    public static Comment.CommentBuilder completeWithoutId() {
        return Comment.builder()
                .content("Test comment")
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39")
                .submissionTime(LocalDateTime.now());
    }

    public static Comment.CommentBuilder completeWithoutIdAndSubmissionTime() {
        return Comment.builder()
                .content("Test comment")
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39");
    }

    public static Comment.CommentBuilder completeWithoutIdAndRecipeId() {
        return Comment.builder()
                .content("Test comment")
                .submissionTime(LocalDateTime.now());
    }

}

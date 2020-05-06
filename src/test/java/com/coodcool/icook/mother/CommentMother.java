package com.coodcool.icook.mother;

import com.coodcool.icook.model.Comment;
import com.coodcool.icook.model.User;

import java.time.LocalDateTime;
import java.time.Month;

public class CommentMother {

    public static Comment.CommentBuilder withoutUserAndId() {
        return Comment.builder()
                .content("This is very delicious and healthy")
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_954a236596ef32f8e013cc9dbf52d0cf")
                .submissionTime(LocalDateTime.of(2019, Month.AUGUST, 10, 10,10,10));
    }

    public static Comment.CommentBuilder withoutUserAndWithCustomId(Long id) {
        return withoutUserAndId()
                .id(id);
    }

    public static Comment.CommentBuilder withoutIdAndWithCustomUser(User user) {
        return withoutUserAndId()
                .user(user);
    }

    public static Comment.CommentBuilder completeWithCustomIdAndUser(Long id, User user) {
        return withoutUserAndWithCustomId(id)
                .user(user);
    }
}

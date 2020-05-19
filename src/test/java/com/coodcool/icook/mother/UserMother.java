package com.coodcool.icook.mother;

import com.coodcool.icook.model.*;

import java.util.Arrays;

public class UserMother {

    public static User.UserBuilder withoutAnyRelationsAndId() {
        return User.builder()
                .userName("chef")
                .firstName("Gordon")
                .email("gordon@ramsey.com")
                .password("password")
                .roles(Arrays.asList(Role.USER));
    }

    public static User.UserBuilder withoutAnyRelationsAndWithCustomId(Long id) {
        return withoutAnyRelationsAndId()
                .id(id);
    }

    public static User.UserBuilder withoutIdAndWithCustomRelations(Address address, Comment comment, FavoriteRecipe favorite, PersonalNote note) {
        return withoutAnyRelationsAndId()
                .address(address)
                .comment(comment)
                .favorite(favorite)
                .note(note);
    }
}

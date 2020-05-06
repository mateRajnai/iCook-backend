package com.coodcool.icook.mother;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.Tag;
import com.coodcool.icook.model.User;

public class TagMother {

    public static Tag.TagBuilder withoutUserAndFavoriteRecipesAndId() {
        return Tag.builder()
                .tag("breakfast");
    }

    public static Tag.TagBuilder withoutUserAndFavoriteRecipesAndWithCustom(Long id) {
        return withoutUserAndFavoriteRecipesAndId()
                .id(id);
    }

    public static Tag.TagBuilder withoutIdAndWithCustom(User user, FavoriteRecipe recipe) {
        return withoutUserAndFavoriteRecipesAndId()
                .user(user)
                .taggedFavorite(recipe);
    }

    public static Tag.TagBuilder withParameterizedIdAndRelations(Long id, User user, FavoriteRecipe recipe) {
        return withoutIdAndWithCustom(user, recipe)
                .id(id);
    }
}

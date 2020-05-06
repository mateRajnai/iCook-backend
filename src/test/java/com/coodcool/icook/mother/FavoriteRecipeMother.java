package com.coodcool.icook.mother;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.User;

public class FavoriteRecipeMother {

    public static FavoriteRecipe.FavoriteRecipeBuilder withoutUserAndId() {
        return FavoriteRecipe.builder()
                .recipeId("long escaped uri nr1");
    }

    public static FavoriteRecipe.FavoriteRecipeBuilder withoutIdAndWithCustomUser(User user) {
        return withoutUserAndId()
                .user(user);
    }

    public static FavoriteRecipe.FavoriteRecipeBuilder withoutUserAndCustom(Long id) {
        return withoutUserAndId()
                .id(id);
    }

    public static FavoriteRecipe.FavoriteRecipeBuilder completeWithCustomIdAndUser(Long id, User user) {
        return withoutUserAndCustom(id)
                .user(user);
    }
}

package com.coodcool.icook.mother;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.User;

public class FavoriteRecipeMother {

    public static FavoriteRecipe.FavoriteRecipeBuilder withoutUserAndId() {
        return FavoriteRecipe.builder()
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39");
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

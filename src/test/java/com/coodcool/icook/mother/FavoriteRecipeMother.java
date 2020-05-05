package com.coodcool.icook.mother;

import com.coodcool.icook.model.FavoriteRecipe;

public class FavoriteRecipeMother {
//"http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_954a236596ef32f8e013cc9dbf52d0cf"

    public static FavoriteRecipe.FavoriteRecipeBuilder completeWithoutId() {
        return FavoriteRecipe.builder()
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39");
    }

    public static FavoriteRecipe.FavoriteRecipeBuilder completeWithCustom(Long id) {
        return FavoriteRecipe.builder()
                .id(id)
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_233567d117b63a90fd90dabbcd4e4f39");
    }
}

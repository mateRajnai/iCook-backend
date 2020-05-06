package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.mother.FavoriteRecipeMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class FavoriteRecipeRepositoryTest {

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Test
    public void saveOneSimple() {
        FavoriteRecipe recipe = FavoriteRecipeMother.withoutUserAndId().build();
        favoriteRecipeRepository.save(recipe);
        List<FavoriteRecipe> allRecipes = favoriteRecipeRepository.findAll();

        assertThat(allRecipes).hasSize(1);
    }
}

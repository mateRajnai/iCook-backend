package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.FavoriteRecipeMother;
import com.coodcool.icook.mother.UserMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class FavoriteRecipeRepositoryTest {

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveOneSimple() {
        FavoriteRecipe recipe = FavoriteRecipeMother
                .withoutUserAndId()
                .build();
        favoriteRecipeRepository.save(recipe);
        List<FavoriteRecipe> allRecipes = favoriteRecipeRepository.findAll();

        assertThat(allRecipes).hasSize(1);
    }

    @Test
    public void getAllSimpleTest() {
        FavoriteRecipe recipe1 = FavoriteRecipeMother
                .withoutUserAndId()
                .build();
        FavoriteRecipe recipe2 = FavoriteRecipeMother
                .withoutUserAndId()
                .recipeId("other id")
                .build();

        recipe1 = favoriteRecipeRepository.save(recipe1);
        recipe2 = favoriteRecipeRepository.save(recipe2);

        List<FavoriteRecipe> savedRecipes = favoriteRecipeRepository.findAll();
        assertThat(savedRecipes)
                .hasSize(2)
                .containsExactlyInAnyOrder(recipe1, recipe2);
    }

    @Test
    public void saveOneWithSimpleUser() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        FavoriteRecipe recipe = FavoriteRecipeMother
                .withoutIdAndWithCustomUser(user)
                .build();
        user.setFavorites(Set.of(recipe));

        favoriteRecipeRepository.saveAndFlush(recipe);
        List<FavoriteRecipe> recipes = favoriteRecipeRepository.findAll();
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getUsers()).hasSize(1);
    }
}

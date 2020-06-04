package com.coodcool.icook.dao.repository;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.Tag;
import com.coodcool.icook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

    Optional<FavoriteRecipe> findByRecipeId(String recipeId);


}

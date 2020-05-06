package com.coodcool.icook.dao.repository;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import com.coodcool.icook.model.FavoriteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

}

package com.coodcool.icook.dao.repository;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import com.coodcool.icook.model.FavoriteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

}

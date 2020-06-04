package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookmarkService {

    private final FavoriteRecipeRepository favorites;
    private final UserRepository users;

    public List<FavoriteRecipe> getAllFavorites() {
        return favorites.findAll();
    }

    public FavoriteRecipe saveFavoriteRecipe(FavoriteRecipe favoriteRecipe) {
        return favorites.save(favoriteRecipe);
    }

    public void deleteFavoriteRecipeBy(Long id) {
        favorites.deleteById(id);
    }
}

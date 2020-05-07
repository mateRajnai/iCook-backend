package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoritesController {

    private FavoriteRecipeRepository favoriteRecipeRepository;

    public FavoritesController(FavoriteRecipeRepository favoriteRecipeRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
    }

    @GetMapping("")
    public List<FavoriteRecipe> getFavoriteRecipes() {
        return this.favoriteRecipeRepository.findAll();
    }

    @PostMapping("")
    public FavoriteRecipe saveFavoriteRecipe(@RequestBody FavoriteRecipe favoriteRecipe) {
       return this.favoriteRecipeRepository.save(favoriteRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteFavoriteRecipe(@PathVariable(value="id") Long id) {
        this.favoriteRecipeRepository.deleteById(id);
    }
}

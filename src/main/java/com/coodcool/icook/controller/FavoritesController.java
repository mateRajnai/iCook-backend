package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private final BookmarkService bookmarkService;

    @GetMapping("")
    public ResponseEntity<List<FavoriteRecipe>> getFavoriteRecipes() {
        return ResponseEntity.ok(bookmarkService.getAllFavorites());
    }

    @PostMapping("")
    public ResponseEntity<FavoriteRecipe> saveFavoriteRecipe(@RequestBody FavoriteRecipe favoriteRecipe) {
       return ResponseEntity.ok(bookmarkService.saveFavoriteRecipe(favoriteRecipe));
    }

    @DeleteMapping("/{id}")
    public void deleteFavoriteRecipe(@PathVariable(value="id") Long id) {
        bookmarkService.deleteFavoriteRecipeBy(id);
    }
}

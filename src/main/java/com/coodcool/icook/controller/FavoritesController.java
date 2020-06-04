package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private final BookmarkService bookmarkService;

    @GetMapping("")
    public ResponseEntity<List<FavoriteRecipe>> getFavoriteRecipes(HttpServletRequest req) {
        return ResponseEntity.ok(bookmarkService.getAllFavoritesOfUser(req));
    }

    @PostMapping("")
    public ResponseEntity<FavoriteRecipe> saveFavoriteRecipe(@RequestBody FavoriteRecipe favoriteRecipe, HttpServletRequest req) {
       return ResponseEntity.ok(bookmarkService.saveFavoriteRecipe(favoriteRecipe, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFavoriteRecipe(@PathVariable(value="id") Long id, HttpServletRequest req) {
        bookmarkService.deleteFavoriteRecipeBy(id, req);
        return ResponseEntity.ok().build();
    }
}

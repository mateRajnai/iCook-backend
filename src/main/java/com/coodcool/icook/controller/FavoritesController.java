package com.coodcool.icook.controller;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoriteRecipeIdsDao favoriteRecipeIdsDao;

    @Autowired
    public FavoritesController(@Qualifier("daoMem") FavoriteRecipeIdsDao favoriteRecipeIdsDao) {
        this.favoriteRecipeIdsDao = favoriteRecipeIdsDao;
    }

    @GetMapping("")
    public Set<String> getFavoriteRecipeIds() {
        return this.favoriteRecipeIdsDao.getAll();
    }

    @PostMapping("/update")
    public void updateFavoriteRecipeIds(@RequestBody String id) {
        this.favoriteRecipeIdsDao.update(id);
    }
}

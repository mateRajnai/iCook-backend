package com.coodcool.icook.controller;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoriteRecipeIdsDao favoriteRecipeIdsDao;

    @Autowired
    public FavoritesController(@Qualifier("daoMem") FavoriteRecipeIdsDao favoriteRecipeIdsDao) {
        this.favoriteRecipeIdsDao = favoriteRecipeIdsDao;
    }

    public FavoritesController() {
    }

    @GetMapping("")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> getFavoriteRecipeIds() {
        return this.favoriteRecipeIdsDao.getAll();
    }

    @PutMapping("")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> updateFavoriteRecipeIds(@RequestBody String id) {
       return this.favoriteRecipeIdsDao.update(id);
    }
}

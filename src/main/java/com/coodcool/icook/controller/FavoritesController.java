package com.coodcool.icook.controller;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoritesController {

    private FavoriteRecipeIdsDao favoriteRecipeIdsDao;

    public FavoritesController(@Qualifier("daoMem") FavoriteRecipeIdsDao favoriteRecipeIdsDao) {
        this.favoriteRecipeIdsDao = favoriteRecipeIdsDao;
    }

    @GetMapping("")
    public List<String> getFavoriteRecipeIds() {
        return this.favoriteRecipeIdsDao.getAll();
    }

    @PutMapping("")
    public List<String> updateFavoriteRecipeIds(@RequestBody String id) {
       return this.favoriteRecipeIdsDao.update(id);
    }
}

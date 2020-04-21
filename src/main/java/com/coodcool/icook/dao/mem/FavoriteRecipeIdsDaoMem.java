package com.coodcool.icook.dao.mem;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;

import java.util.HashSet;
import java.util.Set;

public class FavoriteRecipeIdsDaoMem implements FavoriteRecipeIdsDao {
    Set<String> favoriteRecipeIds = new HashSet<>();

    @Override
    public Set<String> getAll() {
        return this.favoriteRecipeIds;
    }

    @Override
    public void update(String id) {
        if (favoriteRecipeIds.contains(id)) favoriteRecipeIds.remove(id);
        else favoriteRecipeIds.add(id);
    }
}

package com.coodcool.icook.dao;

import java.util.Set;

public interface FavoriteRecipeIdsDao {
    Set<String> getAll();
    void update(String id);
}

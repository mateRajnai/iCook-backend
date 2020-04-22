package com.coodcool.icook.dao;

import java.util.List;

public interface FavoriteRecipeIdsDao {
    List<String> getAll();
    List<String> update(String id);
}

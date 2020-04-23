package com.coodcool.icook.dao.implementation;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Qualifier("daoMem")
public class FavoriteRecipeIdsDaoMem implements FavoriteRecipeIdsDao {
    Set<String> favoriteRecipeIds;

    public FavoriteRecipeIdsDaoMem() {
        this.favoriteRecipeIds = new HashSet<>();
    }

    @Override
    public List<String> getAll() {
        return new ArrayList<String>(this.favoriteRecipeIds);
    }

    @Override
    public List<String> update(String id) {
        if (favoriteRecipeIds.contains(id)) favoriteRecipeIds.remove(id);
        else favoriteRecipeIds.add(id);
        return new ArrayList<String>(this.favoriteRecipeIds);
    }
}

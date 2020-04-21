package com.coodcool.icook.dao.mem;

import com.coodcool.icook.dao.FavoriteRecipeIdsDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Qualifier("daoMem")
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

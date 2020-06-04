package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.User;
import com.coodcool.icook.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BookmarkService {

    private final FavoriteRecipeRepository favorites;
    private final UserRepository users;
    private final JwtTokenServices jwtTokenServices;

    public List<FavoriteRecipe> getAllFavoritesOfUser(HttpServletRequest req) {
        String token = jwtTokenServices.getTokenFromRequest(req);
        String username = jwtTokenServices.getUsernameFromToken(token);
        User user = users.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new ArrayList<>(user.getFavorites());
    }

    public FavoriteRecipe saveFavoriteRecipe(FavoriteRecipe favoriteRecipe, HttpServletRequest req) {
        FavoriteRecipe favoriteRecipeToSave = favorites
                .findByRecipeId(favoriteRecipe.getRecipeId())
                .orElse(favoriteRecipe);

        String token = jwtTokenServices.getTokenFromRequest(req);
        String username = jwtTokenServices.getUsernameFromToken(token);

        User user = users.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not exists"));

        if (user.getFavorites() != null) user.addFavoriteRecipe(favoriteRecipeToSave);
        else user.setFavorites(Set.of(favoriteRecipeToSave));

        if (favoriteRecipeToSave.getUsers() != null) favoriteRecipeToSave.addUser(user);
        else favoriteRecipeToSave.setUsers(Set.of());

        return favorites.save(favoriteRecipeToSave);
    }

    public void deleteFavoriteRecipeBy(Long id, HttpServletRequest req) {
        String token = jwtTokenServices.getTokenFromRequest(req);
        String username = jwtTokenServices.getUsernameFromToken(token);
        User user = users.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.removeFavoriteRecipe(FavoriteRecipe.builder().id(id).build());
        users.save(user);
    }
}

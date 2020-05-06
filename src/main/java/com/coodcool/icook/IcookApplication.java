package com.coodcool.icook;

import com.coodcool.icook.dao.repository.FavoriteRecipeRepository;
import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@SpringBootApplication
public class IcookApplication {

    @Autowired
    FavoriteRecipeRepository favoriteRecipeRepository;


    public static void main(String[] args) {
        SpringApplication.run(IcookApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            FavoriteRecipe favoriteRecipe = FavoriteRecipe.builder()
                    .recipeId("1")
                    .bookmarkedTime(10)
                    .build();

            favoriteRecipeRepository.save(favoriteRecipe);
        };
    }
}

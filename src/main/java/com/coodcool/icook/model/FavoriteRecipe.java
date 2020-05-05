package com.coodcool.icook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FavoriteRecipe {
    @Id
    @GeneratedValue
    private Long id;
    private String recipeId;
}

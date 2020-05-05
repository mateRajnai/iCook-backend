package com.coodcool.icook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FavouriteRecipe {

    @Id
    @GeneratedValue
    private Long id;
    private String recipeId;

    @ManyToMany
    private Set<User> users;

    private int bookmarkedTime;

    @ManyToMany
    private List<Tag> tags;


}

package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<User> users;

    private int bookmarkedTime;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Tag> tags;


}

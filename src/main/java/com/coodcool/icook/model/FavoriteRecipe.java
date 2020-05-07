package com.coodcool.icook.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FavoriteRecipe {
    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String recipeId;
    @Singular
    @ManyToMany(mappedBy = "favorites")
    @EqualsAndHashCode.Exclude
    private Set<User> users;

    private int bookmarkedTime;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Tag> tags;
}

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
    @ToString.Exclude
    @Singular
    @ManyToMany(mappedBy = "favorites")
    @EqualsAndHashCode.Exclude
    private Set<User> users;

    @ToString.Exclude
    private int bookmarkedTime;

    @ToString.Exclude
    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Tag> tags;
}

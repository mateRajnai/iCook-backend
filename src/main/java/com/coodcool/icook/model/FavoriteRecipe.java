package com.coodcool.icook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @EqualsAndHashCode.Exclude
    private String recipeId;
    @ToString.Exclude
    @Singular
    @ManyToMany(mappedBy = "favorites")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> users;
    @ToString.Exclude
    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Tag> tags;

    public boolean addUser(User user) {
        return this.users.add(user);
    }
}

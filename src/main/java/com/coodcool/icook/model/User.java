package com.coodcool.icook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String userName;
    private String firstName;
    private String password;
    @Column(unique=true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private List<Role> roles = new ArrayList<>();


    @OneToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Address address;

    @JsonIgnore
    @ToString.Exclude
    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    Set<Comment> comments;

    @ToString.Exclude
    @Singular
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    Set<FavoriteRecipe> favorites;

    @ToString.Exclude
    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<PersonalNote> notes;

    @ToString.Exclude
    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<Tag> tags;

    public void addPersonalNote(PersonalNote personalNote) {
        notes.add(personalNote);
    }

    public boolean addFavoriteRecipe(FavoriteRecipe favoriteRecipe) {
        return favorites.add(favoriteRecipe);
    }

    public boolean removeFavoriteRecipe(FavoriteRecipe favoriteRecipe) {
        return favorites.remove(favoriteRecipe);
    }
}

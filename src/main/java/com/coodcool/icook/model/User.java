package com.coodcool.icook.model;

import lombok.EqualsAndHashCode;
import lombok.Singular;

import javax.persistence.*;
import java.util.Set;

public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String firstName;
    private String password;
    private String email;
    private Role userType;

    @OneToOne
    private Address address;

    @OneToMany
    Set<Comment> comments;

    @ManyToMany
    Set<FavouriteRecipes> favourites;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<PersonalNote> notes;


}

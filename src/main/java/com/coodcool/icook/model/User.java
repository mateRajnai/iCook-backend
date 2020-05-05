package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<Comment> comments;


    @ManyToMany
    Set<FavouriteRecipe> favourites;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<PersonalNote> notes;


}

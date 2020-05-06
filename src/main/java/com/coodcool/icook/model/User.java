package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.*;
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

    private String userName;
    private String firstName;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role userType;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Address address;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    Set<Comment> comments;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    Set<FavoriteRecipe> favorites;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<PersonalNote> notes;


}

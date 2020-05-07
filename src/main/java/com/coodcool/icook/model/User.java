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
    private String avatar;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    Set<FavoriteRecipe> favorites;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<PersonalNote> notes;

    @Singular
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    Set<Tag> tags;
}

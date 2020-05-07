package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @Column(nullable = false)
    private LocalDateTime submissionTime;

    @Column(nullable = false)
    private String recipeId;
    @ManyToOne
    @ToString.Exclude
    private User user;

}

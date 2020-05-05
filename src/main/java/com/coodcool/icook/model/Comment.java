package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

//    private User user;

}

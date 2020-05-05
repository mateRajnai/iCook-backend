package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    private String id;
    private String content;
    private LocalDateTime submissionTime;
    private String recipeId;
    @ManyToOne
    private User user;

}
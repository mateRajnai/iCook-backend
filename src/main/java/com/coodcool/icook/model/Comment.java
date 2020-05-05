package com.coodcool.icook.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {

    private String id;
    private String content;
//    @Autowired
    private LocalDateTime submissionTime;
    private String recipeId;

    @ManyToOne
    private User user;


}

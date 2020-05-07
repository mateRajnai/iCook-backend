package com.coodcool.icook.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PersonalNote {

    @Id
    @GeneratedValue
    private Long id;

    private String recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    private String content;

    private LocalDateTime submissionTime;


}

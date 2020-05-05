package com.coodcool.icook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;

    private LocalDateTime submissionTime;
}

package com.coodcool.icook.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;
    private String content;
    private LocalDateTime submissionTime;
    private String recipeId;

}

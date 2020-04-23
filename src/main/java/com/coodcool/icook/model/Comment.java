package com.coodcool.icook.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;
    private String content;
    @Autowired
    private LocalDateTime submissionTime;
    private String recipeId;


}

package com.example.Home.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO {

    private int articleID;
    private String image;
    private String topic;
    private String text;
    private LocalDateTime postedDate;
}

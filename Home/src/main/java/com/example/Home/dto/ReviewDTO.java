package com.example.Home.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long revid;
    private String userName;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;

    // Optionally, you can include a ResponseDTO if you want to include response data in ReviewDTO
    private ResponseDTO response;  // Only if you want to include the response in the review DTO
}

package com.example.Home.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNDTO {

    private Long resid;
    private String adminName;
    private String responseText;
    private LocalDateTime respondedAt;
}

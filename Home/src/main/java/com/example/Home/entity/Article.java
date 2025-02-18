package com.example.Home.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleID;
    private String image;

    @Column(columnDefinition = "TEXT")
    private String topic;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "posted_date", columnDefinition = "TIMESTAMP") // Specifies the column type as TIMESTAMP
    private LocalDateTime postedDate;
}

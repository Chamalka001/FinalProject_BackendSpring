package com.example.Home.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revid;

    private String userName;

    @Column(columnDefinition = "TEXT")
    private String comment;  // Review content

    private int rating;  // Rating (1-5 stars)

    private LocalDateTime createdAt;  // Remove default value here

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Response response; // Relationship with Response entity (One-to-One)

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();  // Set current time if not set
        }
        // Format the createdAt to a simpler format
        String formattedDate = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // Now, save formattedDate if needed or store the original LocalDateTime in DB
    }
}

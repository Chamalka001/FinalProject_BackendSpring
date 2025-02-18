package com.example.Home.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "responses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resid;

    @OneToOne
    @JoinColumn(name = "revid", nullable = false)
    private Review review;  // Foreign key linking to Review entity

    private String adminName;  // Name of the admin responding

    @Column(columnDefinition = "TEXT")
    private String responseText;  // Admin's response

    private LocalDateTime respondedAt;  // Remove default value here

    @PrePersist
    public void prePersist() {
        if (respondedAt == null) {
            respondedAt = LocalDateTime.now();  // Set current time if not set
        }
        // Format the createdAt to a simpler format
        String formattedDate = respondedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // Now, save formattedDate if needed or store the original LocalDateTime in DB
    }
}

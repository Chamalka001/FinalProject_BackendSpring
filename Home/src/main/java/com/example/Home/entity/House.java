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
@Table(name = "House")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int houseID;

    private String image;
    private double price;
    private String location;
    private int landSize;
    private int houseSize;
    private int storeys;
    private int beds;
    private int baths;
    private String ownerName;
    private String ownerContact;
    private String description;

    @Column(name = "posted_date", columnDefinition = "TIMESTAMP") // Specifies the column type as TIMESTAMP
    private LocalDateTime postedDate;
}

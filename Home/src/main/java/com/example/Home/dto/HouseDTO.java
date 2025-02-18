package com.example.Home.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseDTO {

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
    private LocalDateTime postedDate;
}

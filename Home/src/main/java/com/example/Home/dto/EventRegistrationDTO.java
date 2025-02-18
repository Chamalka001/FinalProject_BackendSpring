package com.example.Home.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationDTO {

    private Long regid;
    private String userName;
    private String contactInfo;
    private String email;
    private int numberOfSeats;
    private Long eventId;
}

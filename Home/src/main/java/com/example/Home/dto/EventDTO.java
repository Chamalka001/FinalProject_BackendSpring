package com.example.Home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long eventid;
    private String name;
    private String date;
    private String location;
    private String description;
    private List<EventRegistrationDTO> registrations;
}

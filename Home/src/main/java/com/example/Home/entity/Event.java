package com.example.Home.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;

    private String name;
    private String date;
    private String location;
    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRegistration> registrations; // One event -> Many registrations
}

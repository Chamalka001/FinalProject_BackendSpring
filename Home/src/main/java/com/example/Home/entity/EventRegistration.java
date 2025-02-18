package com.example.Home.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registrations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regid;

    private String userName;  // Store user name instead of linking to User table (to avoid relationships)
    private String contactInfo;
    private String email;
    private int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
}

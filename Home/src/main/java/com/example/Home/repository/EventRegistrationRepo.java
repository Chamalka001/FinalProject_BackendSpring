package com.example.Home.repository;

import com.example.Home.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepo extends JpaRepository<EventRegistration,Long> {
    // Custom method to get registrations by event ID
    List<EventRegistration> findByEvent_Eventid(Long eventid);
}

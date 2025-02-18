package com.example.Home.service;


import com.example.Home.dto.EventRegistrationDTO;
import com.example.Home.entity.Event;
import com.example.Home.entity.EventRegistration;
import com.example.Home.repository.EventRegistrationRepo;
import com.example.Home.repository.EventRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepo eventRegistrationRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ModelMapper modelMapper;

    public EventRegistrationDTO saveRegistration(EventRegistrationDTO eventRegistrationDTO) {
        // Fetch the Event from the database using the eventid from the DTO
        Event event = eventRepo.findById(eventRegistrationDTO.getEventId()).orElse(null);

        if (event != null) {
            // Map the EventRegistrationDTO to EventRegistration entity
            EventRegistration eventRegistration = modelMapper.map(eventRegistrationDTO, EventRegistration.class);

            // Set the fetched Event to the EventRegistration entity
            eventRegistration.setEvent(event);

            // Save the EventRegistration entity to the database
            eventRegistrationRepo.save(eventRegistration);

            // Return the saved EventRegistrationDTO
            return modelMapper.map(eventRegistration, EventRegistrationDTO.class);
        } else {
            // If the Event is not found, throw an exception or handle the error
            throw new RuntimeException("Event not found with id: " + eventRegistrationDTO.getEventId());
        }
    }


    public List<EventRegistrationDTO> getAllRegistrations(){
        List<EventRegistration>eventRegistrationList=eventRegistrationRepo.findAll();
        return modelMapper.map(eventRegistrationList,new TypeToken<List<EventRegistrationDTO>>(){}.getType());
    }

    public EventRegistrationDTO updateRegistration(EventRegistrationDTO eventRegistrationDTO){
        eventRegistrationRepo.save(modelMapper.map(eventRegistrationDTO, EventRegistration.class));
        return eventRegistrationDTO;
    }

    public boolean deleteRegistration(EventRegistrationDTO eventRegistrationDTO){
        eventRegistrationRepo.delete(modelMapper.map(eventRegistrationDTO, EventRegistration.class));
        return true;
    }

    public EventRegistrationDTO getRegistrationById(Long regId) {
        EventRegistration eventRegistration = eventRegistrationRepo.findById(regId).orElse(null);
        return modelMapper.map(eventRegistration, EventRegistrationDTO.class);
    }

    // In EventRegistrationService.java
    public List<EventRegistrationDTO> getRegistrationsByEvent(Long eventId) {
        List<EventRegistration> registrations = eventRegistrationRepo.findByEvent_Eventid(eventId);
        return registrations.stream()
                .map(registration -> modelMapper.map(registration, EventRegistrationDTO.class))
                .collect(Collectors.toList());
    }

}

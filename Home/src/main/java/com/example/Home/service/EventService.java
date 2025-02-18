package com.example.Home.service;

import com.example.Home.dto.EventDTO;
import com.example.Home.entity.Event;
import com.example.Home.repository.EventRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ModelMapper modelMapper;

    public EventDTO saveEvent(EventDTO eventDTO){
        eventRepo.save(modelMapper.map(eventDTO, Event.class));
        return eventDTO;
    }

    public List<EventDTO> getAllEvents(){
        List<Event>eventList=eventRepo.findAll();
        return modelMapper.map(eventList,new TypeToken<List<EventDTO>>(){}.getType());
    }

    public EventDTO updateEvent(EventDTO eventDTO){
        eventRepo.save(modelMapper.map(eventDTO, Event.class));
        return eventDTO;
    }

    public boolean deleteEvent(EventDTO eventDTO){
        eventRepo.delete(modelMapper.map(eventDTO, Event.class));
        return true;
    }

    public EventDTO getEventById(Long eventId) {
        Event event = eventRepo.findById(eventId).orElse(null);
        return modelMapper.map(event, EventDTO.class);
    }
}

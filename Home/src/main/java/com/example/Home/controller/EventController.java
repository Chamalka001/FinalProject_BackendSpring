package com.example.Home.controller;


import com.example.Home.dto.EventDTO;
import com.example.Home.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/v1/event")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public List<EventDTO> getEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping("/saveEvent")
    public EventDTO saveEvent(@RequestBody EventDTO eventDTO){
        return eventService.saveEvent(eventDTO);
    }

    @PutMapping("/updateEvent")
    public EventDTO updateEvent(@RequestBody EventDTO eventDTO){
        return eventService.updateEvent(eventDTO);
    }

    @DeleteMapping("/deleteEvent")
    public boolean deleteEvent(@RequestBody EventDTO eventDTO){
        return eventService.deleteEvent(eventDTO);
    }

    @GetMapping("/getEventById/{eventId}")
    public EventDTO getEventById(@PathVariable Long eventId){
        return eventService.getEventById(eventId);
    }
}

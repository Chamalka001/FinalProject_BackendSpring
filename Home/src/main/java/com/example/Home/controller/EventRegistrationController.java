package com.example.Home.controller;

import com.example.Home.dto.EventRegistrationDTO;
import com.example.Home.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/v1/registration")
@RestController
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @GetMapping("/getRegistrations")
    public List<EventRegistrationDTO> getRegistrations(){
        return eventRegistrationService.getAllRegistrations();
    }

    @PostMapping("/saveRegistration")
    public EventRegistrationDTO saveRegistration(@RequestBody EventRegistrationDTO eventRegistrationDTO){
        return eventRegistrationService.saveRegistration(eventRegistrationDTO);
    }

    @PutMapping("/updateRegistration")
    public EventRegistrationDTO updateRegistration(@RequestBody EventRegistrationDTO eventRegistrationDTO){
        return eventRegistrationService.updateRegistration(eventRegistrationDTO);
    }

    @DeleteMapping("/deleteRegistration")
    public boolean deleteRegistration(@RequestBody EventRegistrationDTO eventRegistrationDTO){
        return eventRegistrationService.deleteRegistration(eventRegistrationDTO);
    }

    @GetMapping("/getRegistrationById/{regId}")
    public EventRegistrationDTO getRegistrationById(@PathVariable Long regId){
        return eventRegistrationService.getRegistrationById(regId);
    }

    @GetMapping("/getRegistrationsByEvent/{eventId}")
    public List<EventRegistrationDTO> getRegistrationsByEvent(@PathVariable Long eventId) {
        return eventRegistrationService.getRegistrationsByEvent(eventId);
    }
}

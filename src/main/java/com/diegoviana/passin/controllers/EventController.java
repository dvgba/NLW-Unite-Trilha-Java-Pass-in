package com.diegoviana.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.diegoviana.passin.dto.event.EventIdDTO;
import com.diegoviana.passin.dto.event.EventRequestDTO;
import com.diegoviana.passin.dto.event.EventResponseDTO;
import com.diegoviana.passin.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvento(@PathVariable String id) {
        EventResponseDTO event = this.service.getEventDetail(id);

        return ResponseEntity.ok(event);
    }  
    
    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.service.createEvent(body);

        var uri = uriComponentsBuilder
            .path("/events/{id}")
            .buildAndExpand(eventIdDTO.eventId())
            .toUri();
            

        return ResponseEntity
            .created(uri)
            .body(eventIdDTO);
    }
}

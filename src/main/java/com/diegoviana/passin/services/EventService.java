package com.diegoviana.passin.services;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diegoviana.passin.domain.attendee.Attendee;
import com.diegoviana.passin.domain.event.Event;
import com.diegoviana.passin.dto.event.EventIdDTO;
import com.diegoviana.passin.dto.event.EventRequestDTO;
import com.diegoviana.passin.dto.event.EventResponseDTO;
import com.diegoviana.passin.repositories.AttendeeRepository;
import com.diegoviana.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maxAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","")
            .replaceAll("[^\\w\\s]", "")
            .replaceAll("[\\s+]", "-")
            .toLowerCase();
    }
}

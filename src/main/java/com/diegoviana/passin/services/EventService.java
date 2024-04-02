package com.diegoviana.passin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diegoviana.passin.domain.attendee.Attendee;
import com.diegoviana.passin.domain.event.Event;
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
}

package com.diegoviana.passin.dto.event;

import com.diegoviana.passin.domain.event.Event;

import lombok.Getter;

@Getter
public class EventResponseDTO {
    
    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        this.event = new EventDetailDTO(
            event.getId(),
            event.getTitle(),
            event.getDetails(),
            event.getSlug(),
            event.getMaximumAttendees(),
            numberOfAttendees
        );
    }
}

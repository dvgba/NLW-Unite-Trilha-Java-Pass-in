package com.diegoviana.passin.dto.event;

public record EventRequestDTO(
    String title,
    String details,
    Integer maxAttendees) {
    
}

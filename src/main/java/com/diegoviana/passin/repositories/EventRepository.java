package com.diegoviana.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegoviana.passin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String> {
    
}

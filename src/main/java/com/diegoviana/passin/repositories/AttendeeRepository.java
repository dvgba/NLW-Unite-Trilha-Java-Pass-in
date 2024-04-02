package com.diegoviana.passin.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.diegoviana.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{
    
    public List<Attendee> findByEventId(
        String eventId);
}

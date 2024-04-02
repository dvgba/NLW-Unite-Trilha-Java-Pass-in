package com.diegoviana.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegoviana.passin.domain.checkin.CheckIn;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer>{
    
}

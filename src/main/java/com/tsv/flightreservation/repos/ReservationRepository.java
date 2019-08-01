package com.tsv.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsv.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
